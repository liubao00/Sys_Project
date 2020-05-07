$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'v1/b/baddressusdt/list',
        datatype: "json",
        colModel: [
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '地址', name: 'address', index: 'address', width: 80 },
			{ label: '星空来客人', name: 'value', index: 'value', width: 80 },
			{ label: '状态', name: 'status', index: 'status', width: 80 ,formatter:function(value, options, row) {
                    var result="";
                    if(value==0){
                        result= '<span class="label label-success">未使用</span>';
                    }else if(value==1){
                        result='<span class="label label-danger">已使用</span>';
                    }
                    return result;
                }},
			{ label: '备注', name: 'remark', index: 'remark', width: 80 },
			{ label: '创建时间', name: 'createTime', index: 'create_time', width: 80 },
			{ label: '更新时间', name: 'updateTime', index: 'update_time', width: 80 }
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
		bAddressUsdt: {}
	},
	methods: {
        addBatch: function () {
            var url = "v1/b/baddressusdt/saveBatch";
            var data = {"number" : 500};
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: data,
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
        },

		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.bAddressUsdt = {};
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
                var url = vm.bAddressUsdt.id == null ? "v1/b/baddressusdt/save" : "v1/b/baddressusdt/update";
                $.ajax({
                    type: "POST",
                    url: baseURL + url,
                    contentType: "application/json",
                    data: JSON.stringify(vm.bAddressUsdt),
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
                        url: baseURL + "v1/b/baddressusdt/delete",
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
			$.get(baseURL + "v1/b/baddressusdt/info/"+id, function(r){
                vm.bAddressUsdt = r.bAddressUsdt;
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
