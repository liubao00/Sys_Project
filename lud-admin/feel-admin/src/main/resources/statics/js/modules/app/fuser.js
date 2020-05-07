$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'v1/b/fuser/list',
        datatype: "json",
        colModel: [
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '用户名', name: 'username', index: 'username', width: 80 }, 
			{ label: '用户状态', name: 'status', index: 'status', width: 80 , formatter: function(value, options, row){
                    var result="";
                    if(value==1){
                        result= '<span class="label label-success">正常</span>';
                    }else if(value==2){
                        result='<span class="label label-danger">冻结</span>';
                    }else if(value==3){
                        result='<span class="label label-danger">禁用</span>';
                    }
                    return result;

                }},
			{ label: '角色id', name: 'roleId', index: 'role_id', width: 80 }, 
			{ label: '邀请码', name: 'invitationCode', index: 'invitation_code', width: 80 }, 
			{ label: '身份证', name: 'idCard', index: 'id_card', width: 80 }, 
			{ label: '真实姓名', name: 'realName', index: 'real_name', width: 80 }, 
			/*{ label: '身份证正面', name: 'idcardFront', index: 'idcard_front', width: 80 },
			{ label: '身份证反面', name: 'idcardReverse', index: 'idcard_reverse', width: 80 }, */
			{ label: '性别', name: 'gender', index: 'gender', width: 80, formatter: function(value, options, row){
                    var result="";
                     if(value==1){
                        result='男';
                    }else if(value==2){
                        result='女';
                    }
                    return result;

                }},
			{ label: '昵称', name: 'nickname', index: 'nickname', width: 80 }, 
			{ label: '城市', name: 'city', index: 'city', width: 80 }, 
			{ label: 'usdt钱包地址', name: 'addressUsdt', index: 'address_usdt', width: 80 }, 
			{ label: 'eth钱包地址', name: 'addressEth', index: 'address_eth', width: 80 }, 
			{ label: '审核状态', name: 'type', index: 'type', width: 80, formatter: function(value, options, row){
                  var result="";
                  if(value==0){
                      result= '<span class="label label-danger">未审核</span>';
                  }else if(value==1){
                        result='<span class="label label-success">审核成功</span>';
                  }else if(value==2){
                      result='<span class="label label-danger">审核拒绝</span>';
                  }
                  return result;

                }},
            { label: '是否复投', name: 'automaticOrder', index: 'create_time', width: 80 , formatter: function(value, options, row){
                    var result="";
                    if(value==0){
                        result= '<span class="label label-danger">否</span>';
                    }else if(value==1){
                        result='<span class="label label-success">是</span>';
                    }
                    return result;

                }},
            { label: '上一次登录的ip地址', name: 'lastLoginIp', index: 'last_login_ip', width: 80 },
			{ label: '上一次登录时间', name: 'lastLoginTime', index: 'last_login_time', width: 80 }, 
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
		fUser: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.fUser = {};
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
                var url = vm.fUser.id == null ? "v1/b/fuser/save" : "v1/b/fuser/update";
                $.ajax({
                    type: "POST",
                    url: baseURL + url,
                    contentType: "application/json",
                    data: JSON.stringify(vm.fUser),
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
                        url: baseURL + "v1/b/fuser/delete",
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
			$.get(baseURL + "v1/b/fuser/info/"+id, function(r){
                vm.fUser = r.fUser;
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
