$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'v1/b/fuserscore/list',
        datatype: "json",
        colModel: [
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '用户ID', name: 'userId', index: 'user_id', width: 80 },
			{ label: '可用投资余额', name: 'score', index: 'score', width: 80 },
			{ label: '冻结投资余额', name: 'freezeScore', index: 'freeze_score', width: 80 },
			{ label: '收益余额', name: 'earningsScore', index: 'earnings_score', width: 80 },
/*
			{ label: '积分类型1：佣金2：限购3：商城', name: 'scoreType', index: 'score_type', width: 80 },
*/
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
		fUserScore: {}
	},
	methods: {
        updateScore: function () {
            var url = "v1/b/fuserscore/updateScore";


            layer.confirm("请确认",{
                content:'确定为用户['+ vm.fUserScore.userId +']拨发积分?',
                btn:['确定','取消']
            },
                function () {
                    var data = {
                        "userId":vm.fUserScore.userId,
                        "score" :vm.fUserScore.changeScore,
                        "scoreType"  : 1
                    };
                    $.ajax({
                        type: "POST",
                        url: baseURL + "v1/b/fuserscore/updateScore",
                        contentType: "application/json",
                        data: JSON.stringify(data),
                        success: function(r){
                            if(r.code == 0){
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
                function(){
                }
            )
        },
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.fUserScore = {};
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
                var url = vm.fUserScore.id == null ? "v1/b/fuserscore/save" : "v1/b/fuserscore/updateScore";

                // $.ajax({
                //     type: "POST",
                //     url: baseURL + url,
                //     contentType: "application/json",
                //     data: JSON.stringify(data),
                //     success: function(r){
                //         if(r.code === 0){
                //              layer.msg("操作成功", {icon: 1});
                //              vm.reload();
                //              $('#btnSaveOrUpdate').button('reset');
                //              $('#btnSaveOrUpdate').dequeue();
                //         }else{
                //             layer.alert(r.msg);
                //             $('#btnSaveOrUpdate').button('reset');
                //             $('#btnSaveOrUpdate').dequeue();
                //         }
                //     }
                // });
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
                        url: baseURL + "v1/b/fuserscore/delete",
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
			$.get(baseURL + "v1/b/fuserscore/info/"+id, function(r){
                vm.fUserScore = r.fUserScore;
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
