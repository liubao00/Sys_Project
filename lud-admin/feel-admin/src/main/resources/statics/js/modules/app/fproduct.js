$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'v1/b/fproduct/list',
        datatype: "json",
        colModel: [
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '排单名称', name: 'name', index: 'name', width: 80 },
			{ label: '排单图片', name: 'pic', index: 'pic', width: 80 },
			{ label: '排单金额', name: 'score', index: 'score', width: 80 },
			{ label: '排单商品状态', name: 'status', index: 'status', width: 80 ,formatter:function(value, options, row) {
                    var result="";
                    if(value==1){
                        result= '<span class="label label-success">正常</span>';
                    }else if(value==2){
                        result='<span class="label label-success">已售完</span>';
                    }
                    return result;
                }},
			{ label: '排单商品描述', name: 'remark', index: 'remark', width: 80 },
			{ label: '规格描述', name: 'specificationDesc', index: 'specification_desc', width: 80 },
			{ label: '规格', name: 'specification', index: 'specification', width: 80 },
			{ label: '静态奖励百分比', name: 'singleBack', index: 'single_back', width: 80 },
			{ label: '累计奖励百分比', name: 'doubleBack', index: 'double_back', width: 80 },
			{ label: '上限奖励百分比', name: 'maxBack', index: 'max_back', width: 80 },
			{ label: '创建时间', name: 'createTime', index: 'create_time', width: 80 },
			{ label: '修改时间', name: 'updateTime', index: 'update_time', width: 80 }
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
		fProduct: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.fProduct = {};
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
                var url = vm.fProduct.id == null ? "v1/b/fproduct/save" : "v1/b/fproduct/update";
                $.ajax({
                    type: "POST",
                    url: baseURL + url,
                    contentType: "application/json",
                    data: JSON.stringify(vm.fProduct),
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
                        url: baseURL + "v1/b/fproduct/delete",
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
			$.get(baseURL + "v1/b/fproduct/info/"+id, function(r){
                vm.fProduct = r.fProduct;
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
