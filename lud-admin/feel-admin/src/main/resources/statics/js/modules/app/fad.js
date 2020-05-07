$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'v1/b/ad/list',
        datatype: "json",
        colModel: [
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '广告标题', name: 'name', index: 'name', width: 80 },
			{ label: '链接地址', name: 'link', index: 'link', width: 80 },
			{ label: '广告宣传图片', name: 'url', index: 'url', width: 80 },
			{ label: '广告位置', name: 'position', index: 'position', width: 80 ,formatter:function (value, options, row) {
                    var result="";
                    if(value==1){
                        result= '<span class="label label-success">首页轮播</span>';
                    }else if(value==2){
                        result='<span class="label label-success">页广告位</span>';
                    }else if(value==3){
                        result='<span class="label label-success">商户广告</span>';
                    }else if(value==4){
                        result='<span class="label label-success">公告</span>';
                    }
                    return result;
                } },
			{ label: '活动内容', name: 'content', index: 'content', width: 80 },
			/*{ label: '类型:1banaer 2xxx', name: 'type', index: 'type', width: 80 },*/
			{ label: '状态', name: 'status', index: 'status', width: 80 ,formatter:function (value, options, row) {
                    var result="";
                    if(value==1){
                        result= '<span class="label label-success">启用</span>';
                    }else if(value==2){
                        result='<span class="label label-danger">停用</span>';
                    }
                    return result;

                }},
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
		fAd: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.fAd = {};
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
                var url = vm.fAd.id == null ? "v1/b/ad/save" : "v1/b/ad/update";
                $.ajax({
                    type: "POST",
                    url: baseURL + url,
                    contentType: "application/json",
                    data: JSON.stringify(vm.fAd),
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
                        url: baseURL + "v1/b/ad/delete",
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
			$.get(baseURL + "v1/b/ad/info/"+id, function(r){
                vm.fAd = r.fAd;
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
