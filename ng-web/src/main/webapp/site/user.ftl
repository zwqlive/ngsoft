<#include "../master.ftl">
    <@layout js="site/user.js">
    <div ms-controller="ctrl">
    <form id="f_user" ms-submit="submit">
    <table>
        <tr>
            <td>username:</td>
            <td> <input type="text" name="username" ms-duplex="data.username" /></td>
        </tr>
        <tr>
            <td>showname:</td>
            <td><input type="text" name="showname" ms-duplex="data.showname" /></td>
        </tr>
        <tr>
            <td>password:</td>
            <td><input type="password" name="password" ms-duplex="data.password" /></td>
        </tr>
        <tr>
            <td><input type="button" ms-click="submit" value="保存" ></td>
            <td>{{data.username}},{{data.showname}},{{data.password}}</td>
        </tr>
    </table>
    </form>
    </div>
</@layout>