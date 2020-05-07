$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'v1/b/fchargerecord/list',
        datatype: "json",
        colModel: [
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '用户ID', name: 'userId', index: 'user_id', width: 80 },
			{ label: '冲币个数', name: 'number', index: 'number', width: 80 },
			{ label: '积分数', name: 'score', index: 'score', width: 80 },
			{ label: '冲币hash值', name: 'hash', index: 'hash', width: 80 },
			{ label: '冲币账号', name: 'account', index: 'account', width: 80 },
			{ label: '冲币凭证', name: 'pic', index: 'pic', width: 80 },
			{ label: '状态：1申请中 2审核通过完成 3审核不通过', name: 'state', index: 'state', width: 80 ,formatter:function(value, options, row) {
                    var result="";
                    if(value==1){
                        result= '<span class="label label-info">申请中</span>';
                    }else if(value==2){
                        result='<span class="label label-success">审核通过完成</span>';
                    }else if(value==3){
                        result='<span class="label label-danger">审核不通过</span>';
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
		fChargeRecord: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.fChargeRecord = {};
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
                var url = vm.fChargeRecord.id == null ? "v1/b/fchargerecord/save" : "v1/b/fchargerecord/update";
                $.ajax({
                    type: "POST",
                    url: baseURL + url,
                    contentType: "application/json",
                    data: JSON.stringify(vm.fChargeRecord),
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
                        url: baseURL + "v1/b/fchargerecord/delete",
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
			$.get(baseURL + "v1/b/fchargerecord/info/"+id, function(r){
                vm.fChargeRecord = r.fChargeRecord;
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
