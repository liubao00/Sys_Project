$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'v1/b/fuserscoredetail/list',
        datatype: "json",
        colModel: [
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '用户ID', name: 'userId', index: 'user_id', width: 80 }, 
			{ label: '订单ID', name: 'orderId', index: 'order_id', width: 80 }, 
			{ label: '用户当前可用积分', name: 'score', index: 'score', width: 80 }, 
			{ label: '用户当前冻结积分', name: 'freezeScore', index: 'freeze_score', width: 80 }, 
			{ label: '操作类型；1：增加 2：减少', name: 'operateType', index: 'operate_type', width: 80 }, 
			{ label: '本次操作积分', name: 'operateScore', index: 'operate_score', width: 80 }, 
			{ label: '操作积分的业务类型', name: 'detailType', index: 'detail_type', width: 80 }, 
			{ label: '关联用户积分表', name: 'userScoreId', index: 'user_score_id', width: 80 }, 
			{ label: '积分类型', name: 'scoreType', index: 'score_type', width: 80 }, 
			{ label: '状态：1.冻结中，2.冻结失败返现 3.冻结失败扣除4.已完成', name: 'status', index: 'status', width: 80 }, 
			{ label: '自动排单：0否，1是', name: 'automaticOrder', index: 'automatic_order', width: 80 }, 
			{ label: '备注，业务单据号，奖励类别等', name: 'remark', index: 'remark', width: 80 }, 
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
		fUserScoreDetail: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.fUserScoreDetail = {};
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
                var url = vm.fUserScoreDetail.id == null ? "v1/b/fuserscoredetail/save" : "v1/b/fuserscoredetail/update";
                $.ajax({
                    type: "POST",
                    url: baseURL + url,
                    contentType: "application/json",
                    data: JSON.stringify(vm.fUserScoreDetail),
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
                        url: baseURL + "v1/b/fuserscoredetail/delete",
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
			$.get(baseURL + "v1/b/fuserscoredetail/info/"+id, function(r){
                vm.fUserScoreDetail = r.fUserScoreDetail;
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
