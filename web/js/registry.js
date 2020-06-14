function reg_account_error()
{
    user=document.getElementById("frmLogin").username.value;
    if(user==""){
        document.getElementById("reg_account_error").innerHTML="B\u1ea1n phải nhập Tài khoản";
        document.getElementById("reg_account_error").style.display="block";
        return false;
    }
    if(user.length<6||user.length>24){
        document.getElementById("reg_account_error").innerHTML="Tên \u0111ăng nhập không hợp lệ";
        document.getElementById("reg_account_error").style.display="block";
        return false;
    }
    else{
        document.getElementById("reg_account_error").style.display="none";
        return true;
    }
}
function reg_pwd_error()
{
    pw=document.getElementById("frmLogin").password.value.length;
    if(pw<6||pw>32){
        document.getElementById("reg_pwd_error").style.display="block";
        return false;
    }
    else{
        document.getElementById("reg_pwd_error").style.display="none";
        return true;
    }
}
function reg_cpwd_error()
{
    pw=document.getElementById("frmLogin").password.value;
    cpw=document.getElementById("frmLogin").cpassword.value;
    if(pw!=cpw){
        document.getElementById("reg_cpwd_error").style.display="block";
        document.getElementById("frmLogin").cpassword.value="";
        return false;
    }
    else{
        document.getElementById("reg_cpwd_error").style.display="none";
        return true;
    }
}
function submit_signup(check)
{
    if(reg_account_error())
        if(reg_pwd_error())
            if(reg_cpwd_error()&&check){
                document.getElementById("reg_account_error").innerHTML="Tài kho\u1ea3n này đã tồn tại";
                document.getElementById("reg_account_error").style.display="block";
            }
}