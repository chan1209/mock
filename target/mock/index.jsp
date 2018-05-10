<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD//XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/html;charset=utf-8"/>
    <title>mock操作步骤</title>
    <style type="text/css">
        body{text-align: center;margin-left: auto;margin-right: auto;width: 800px;}
        .operation-1{text-align: left;}
    </style>
</head>
<body>
<div  class="main-page-total">
    <div  class="operation-1">
      <h1>自己配置请求节点和返回数据!</h1>
      <h1>第一步:查询当前的hosts是否是自己想要的!</h1>
      <form action="/hostsSet/query" mothod="post">
         <p><input type="submit" value="submit"></p>
      </form>
    </div>

    <div class="operation-1">
        <h1>第二步:设定mock的ip或者域名.例如:http://www.jd.com;注意http和https的区分.如果第一部的结果是你想设定的ip或者域名,请跳过这步!</h1>
        <form action="/hostsSet/update" mothod="post">
        <p 请输入想设定的域名><input type="text" id="hostsSet" name="hostsSet"></p>
        <p><input type="submit" value="submit"></p>
        </form>
    </div>

    <div class="operation-1">
        <h1>第三步:维护设定域名下的访问节点和返回值!</h1>
        <h2>设置mock参数</h2>
        <form action="/dataManger/insert" method="post">
            <p>mock name:</p><input type="text" id="mockServiceName" name="mockServiceName">
            <p>request Url path (格式如:/hostsSet/query):</p><input type="text" id="mockUrlPath" name="mockUrlPath">
            <p>response data(json格式):</p><textarea type="text" id="mockResponse" name="mockResponse"></textarea>
            <p>params data(json格式):</p><textarea type="text" id="mockParams" name="mockParams"></textarea>
            <p><input type="submit" value="submit"></p>
        </form>
    </div>

    <div class="operation-1">
        <h2>删除mock参数</h2>
        <form action="/dataManger/deleteMockDataByID" method="post">
        <p>mock data id</p><input type="text" id="id" name="id">
        <p><input type="submit" value="submit"></p>
        </form>

        <h2>查询所有的mock数据</h2>
        <form action="/dataManger/getAllMockList" method="post">
          <p><input type="submit" value="submit"></p>
        </form>
    </div>
</div>
</body>
</html>
