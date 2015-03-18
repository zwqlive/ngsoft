﻿<#include "master.ftl">
<@layout js="login.js">
<link href="${root!}/assets/style/login.css" rel="stylesheet" type="text/css"/>

<DIV class="top_div"></DIV>
<DIV  style="background: rgb(255, 255, 255); margin: -100px auto auto; border: 1px solid rgb(231, 231, 231); border-image: none; width: 400px; height: 200px; text-align: center;">
    <DIV style="width: 165px; height: 96px; position: absolute;">
        <DIV class="tou"></DIV>
        <DIV class="initial_left_hand" id="left_hand"></DIV>
        <DIV class="initial_right_hand" id="right_hand"></DIV>
    </DIV>
    <form ms-controller="login-form" >
    <P style="padding: 30px 0px 10px; position: relative;"><SPAN
            class="u_logo"></SPAN>
        <INPUT ms-duplex="username" class="ipt" type="text" placeholder="请输入用户名" value="admin">
    </P>
    <P style="position: relative;"><SPAN class="p_logo"></SPAN>
        <INPUT ms-duplex="password" class="ipt" id="password" type="password" placeholder="请输入密码" value="admin">
    </P>

    <DIV style="height: 50px; line-height: 50px; margin-top: 30px; border-top-color: rgb(231, 231, 231); border-top-width: 1px; border-top-style: solid;">
        <P style="margin: 0px 35px 20px 45px;">
                <SPAN style="float: left;"><A style="color: rgb(204, 204, 204);"
                                              href="#">忘记密码?</A></SPAN>
           <SPAN style="float: right;"><A style="color: rgb(204, 204, 204); margin-right: 10px;"
                                          href="#">注册</A>
              <A style="background: rgb(0, 142, 173); padding: 7px 10px; border-radius: 4px; border: 1px solid rgb(26, 117, 152); border-image: none; color: rgb(255, 255, 255); font-weight: bold;"
                 href="#">登录</A>
           </SPAN></P></DIV>
    </form>
</DIV>

<div style="text-align:center;">
</div>
</@layout>