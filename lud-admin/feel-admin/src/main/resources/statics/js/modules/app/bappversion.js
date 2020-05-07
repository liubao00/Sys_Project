$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'v1/b/bappversion/list',
        datatype: "json",
        colModel: [
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: 'URL地址', name: 'url', index: 'url', width: 80 },
			{ label: '类型', name: 'type', index: 'type', width: 80 ,formatter:function (value, options, row) {
                    var result="";
                    if(value==1){
                        result= '<span class="label label-success">ios</span>';
                    }else if(value==2){
                        result='<span class="label label-success">apk</span>';
                    }
                    return result;
                }},
			{ label: '修改时间', name: 'updateDate', index: 'update_date', width: 80 },
			{ label: '版本号', name: 'version', index: 'version', width: 80 },
			{ label: '更新说明', name: 'remark', index: 'remark', width: 80 },
			{ label: '状态', name: 'status', index: 'status', width: 80 ,formatter:function (value, options, row) {
                    var result="";
                    if(value==0){
                        result= '<span class="label label-success">无效</span>';
                    }else if(value==1){
                        result='<span class="label label-success">生效</span>';
                    }
                    return result;
                }},
            { label: '创建时间', name: 'createDate', index: 'create_date', width: 80 }
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true,
        rownumWidth: 25,
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page",
            rows:"limit",
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
        }
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		bAppVersion: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.bAppVersion = {};
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";

            vm.getInfo(id)
		},
		saveOrUpdate: function (event) {
		    $('#btnSaveOrUpdate').button('loading').delay(1000).queue(function() {
                var url = vm.bAppVersion.id == null ? "v1/b/bappversion/save" : "v1/b/bappversion/update";
                $.ajax({
                    type: "POST",
                    url: baseURL + url,
                    contentType: "application/json",
                    data: JSON.stringify(vm.bAppVersion),
                    success: function(r){
                        if(r.code === 0){
                             layer.msg("操作成功", {icon: 1});
                             vm.reload();
                             $('#btnSaveOrUpdate').button('reset');
                             $('#btnSaveOrUpdate').dequeue();
                        }else{
                            layer.alert(r.msg);
                            $('#btnSaveOrUpdate').button('reset');
                            $('#btnSaveOrUpdate').dequeue();
                        }
                    }
                });
			});
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			var lock = false;
            layer.confirm('确定要删除选中的记录？', {
                btn: ['确定','取消'] //按钮
            }, function(){
               if(!lock) {
                    lock = true;
		            $.ajax({
                        type: "DELETE",
                        url: baseURL + "v1/b/bappversion/delete",
                        contentType: "application/json",
                        data: JSON.stringify(ids),
                        success: function(r){
                            if(r.code == 0){
                                layer.msg("操作成功", {icon: 1});
                                $("#jqGrid").trigger("reloadGrid");
                            }else{
                                layer.alert(r.msg);
                            }
                        }
				    });
			    }
             }, function(){
             });
		},
		getInfo: function(id){
			$.get(baseURL + "v1/b/bappversion/info/"+id, function(r){
                vm.bAppVersion = r.bAppVersion;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
                page:page
            }).trigger("reloadGrid");
		}
	}
});
