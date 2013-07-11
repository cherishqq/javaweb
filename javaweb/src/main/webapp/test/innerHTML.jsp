<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>index</title>

<!-- if use <script /> not <script></script> it will display unusual  -->
 

<!--  test  -->
<script type="text/javascript">	
	function innerHTMLDemo()  {
	test_id1.innerHTML="<i><u>设置或获取位于对象起始和结束标签内的 HTML.</u></i>";   
	}
	function innerTextDemo(){
	test_id2.innerText="<i><u>设置或获取位于对象起始和结束标签内的文本.</u></i>";
	}

  	function outerHTMLDemo(){
		test_id3.outerHTML="<font size=9pt color=red><i><u>设置或获取对象及其内容的 HTML 形式.</u></i></font>";
	}
	
	function outerTextDemo(){
	test_id4.outerText="<br></br><i><u>设置(包括标签)或获取(不包括标签)对象的文本.</u></i>"
	}
</script>

</head>
<body>
  
   <ul>
     	<li id="test_id1" onclick="innerHTMLDemo()">innerHTML效果.</li>
     	<li id="test_id2" onclick="innerTextDemo()">innerText效果.</li>
     	<li id="test_id3" onclick="outerHTMLDemo()">outerHTML效果.</li>
     	<li id="test_id4" onclick="outerTextDemo()">outerText效果.</li>
   </ul>

</body>
</html>