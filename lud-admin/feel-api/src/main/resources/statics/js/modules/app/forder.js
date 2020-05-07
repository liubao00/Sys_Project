$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'v1/b/forder/list',
        datatype: "json",
        colModel: [
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '订单编号', name: 'serialNo', index: 'serial_no', width: 80 }, 
			{ label: '用户id', name: 'userId', index: 'user_id', width: 80 }, 
			{ label: '用户名', name: 'userName', index: 'user_name', width: 80 }, 
			{ label: '商品id', name: 'productId', index: 'product_id', width: 80 }, 
			{ label: '商品名', name: 'productName', index: 'product_name', width: 80 }, 
			{ label: '图片', name: 'picture', index: 'picture', width: 80 }, 
			{ label: '单价', name: 'unitPrice', index: 'unit_price', width: 80 }, 
			{ label: '复投次数', name: 'times', index: 'times', width: 80 }, 
			{ label: '单品总价', name: 'totalPrice', index: 'total_price', width: 80 }, 
			{ label: '返还佣金', name: 'scoreBack', index: 'score_back', width: 80 }, 
			{ label: '支付时间', name: 'startTime', index: 'start_time', width: 80 }, 
			{ label: '取消时间', name: 'endTime', index: 'end_time', width: 80 }, 
			{ label: '商品状态 0待支付，1支付成功 2订单失效 3', name: 'status', index: 'status', width: 80 }, 
			{ label: '备注', name: 'remark', index: 'remark', width: 80 }, 
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
		fOrder: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.fOrder = {};
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
                var url = vm.fOrder.id == null ? "v1/b/forder/save" : "v1/b/forder/update";
                $.ajax({
                    type: "POST",
                    url: baseURL + url,
                    contentType: "application/json",
                    data: JSON.stringify(vm.fOrder),
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
                        url: baseURL + "v1/b/forder/delete",
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
			$.get(baseURL + "v1/b/forder/info/"+id, function(r){
                vm.fOrder = r.fOrder;
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
