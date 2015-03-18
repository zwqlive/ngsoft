/**
 * Created by will on 2015-3-9.
 */
var vm = avalon.define({
    $id:"ctrl",
    data:{username:"",
    showname:"",
    password:""},
    submit:function(){
        postData();
    }
});

function postData(){
    $.ajax({
        type:"POST",
        url:"/user/addUser",
        //data:{username:"ss",showname:"will",password:"1234"},
        data:vm.data.$model,
        dataType:"json",
        success:function(data){
            if (200 == data.status) {
                alert("成功");
            } else{
                alert("失败："+data.msg);
            }
        },
        error:function(data){
            alert('post err:'+data.statusText);
        }
    });
}


