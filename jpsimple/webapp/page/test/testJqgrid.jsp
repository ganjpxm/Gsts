<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/page/inc/taglib.jsp" %>
<html>
<head>
  <title><fmt:message key="test.jqgrid.tile"/></title>
  <%@ include file="/page/inc/headjquery.jsp" %>
</head>
<body>
<%@ include file="/page/inc/header.jsp" %>
<div class="main">
	<!-- 标题 -->
	<div id="header" class="dp100 title1">
		<div class="content"><fmt:message key="test.jqgrid.tile"/></div>
	</div>
	<div id="body" class="dp100 mt1">
		<table id="grid1" cellpadding="0" cellspacing="0"></table>
		<div id="pageToolBar1"></div>
	</div>
</div>		
</body>
</html>
<script type="text/javascript">
<!--
jQuery(document).ready(function(){  

	jQuery("#grid1").jqGrid({
     	caption: "<fmt:message key='test.jqgrid.tile'/>", //设置Grid表格的标题，如果未设置，则标题区域不显示。如果设置了，则将显示在Grid的Header层。 
		height: 300, //Grid的高度，可以接受数字、%值、auto，默认值为150。
        width: document.body.clientWidth/100*90, //Grid的宽度，如果未设置，则宽度应为所有列宽的之和；如果设置了宽度，则每列的宽度将会根据shrinkToFit选项的设置，进行设置。
        autowidth: false, //设定列宽，true则自动列宽
        shrinkToFit: true, //此选项用于根据width计算每列宽度的算法。默认值为true。如果shrinkToFit为true且设置了width值，则每列宽度会根据 width成比例缩放；如果shrinkToFit为false且设置了width值，则每列的宽度不会成比例缩放，而是保持原有设置，而Grid将会有水平滚动条。
        /**	使用本地数组插入方式
        datatype: "local",*/
        /** 从后台获取数据，并返回json数据 */
     	url: "test?action=getJqgridJsonData", //通过这个参数得到需要显示的数据，具体的返回值可以使XML也可以是Json。
     	editurl: "test?action=saveJqgridData",
     	datatype: "json", //设定将要得到的数据类型，其余的类型还包括：xml、xmlstring、local、javascript、 function。 
     	mtype: "post",    //定义使用哪种方法发起请求，GET或者POST。 
     	/** 本地json传值
        datatype: "jsonstring", //数据格式类型，local:本地数据, xml:xml数据格式 
        datastr: "{page:1,total:1,records:1,rows:[{id:'1',birthDate:'1990-10-01',name:'小红',amount:200.00,note:'备注1'}]}",
        */
        /** 使用函数提交到后台处理,并返回json格式 
        datatype: function (pdata) {
			$.ajax({
				url: "json?action=getJqGridData",
				data: pdata,
				dataType: "json",
				complete: function(jsondata,stat){
					if(stat=="success") {
						jQuery("#grid1")[0].addJSONData(eval("("+jsondata.responseText+")"))
					}
				}
			});
		},*/
        prmNames: {page:'pageNo', rows:'rows', sort:'sidx', order:'sord', id:"id"}, //用于设置jqGrid将要向Server传递的参数名称 
        postData: {data:'测试'}, //自定义请求参数
        jsonReader: {repeatitems:false, page:'pageNo', total:'total', records:'records'}, //用来设定如何解析从Server端发回来的json数据
        colNames: ['编号', '日期', '客户名称', '金额', '备注'],  
        colModel: [  
             {name:'id',  //为Grid中的每个列设置唯一的名称，这是一个必需选项，其中保留字包括subgrid、cb、rn。      
              index:'id', //设置排序时所使用的索引名称，这个index名称会作为sidx参数（prmNames中设置的）传递到Server 
              //label ：当jqGrid的colNames选项数组为空时，为各列指定题头。如果colNames和此项都为空时，则name选项值会成为题头。      
              width:60,    //设置列的宽度，目前只能接受以px为单位的数值，默认为150。
              editable:true, 
              sorttype:'int'
              //sortable ：设置该列是否可以排序，默认为true。
              //search ：设置该列是否可以被列为搜索条件，默认为true。
              //resizable ：设置列是否可以变更尺寸，默认为true。
              //hidden ：设置此列初始化时是否为隐藏状态，默认为false。
              //formatter ：预设类型或用来格式化该列的自定义函数名。常用预设格式有：integer、date、currency、number等
              }, //width:'20%' 
             //key:false, fixed:false, edittype:"text", editoptions:{size:10}   
             {name:'birthDate',index:'birthDate', width:150, editable:true, sorttype:'date'},  
             {name:'name',     index:'name',      width:100, editable:true},  
             {name:'amount',   index:'amount',    width:80,  editable:true, sorttype:'float',  align:'right'},  
             {name:'note',     index:'note',      width:150, editable:true, sortable:false, edittype:"textarea", editoptions:{rows:"2",cols:"20"}}         
        ],
        sortname: "id",     //指定默认的排序列，可以是列名也可以是数字。此参数会在被传递到Server端。 
        sortorder: "desc",
        gridview: true,
        multiselect: true,  //是否支持多选
		cellEdit: true,     //是否可编辑
		pager: "#pageToolBar1", //定义页码控制条Page Bar 
		viewrecords: true,  //设置是否在Pager Bar显示所有记录的总数。
		rowNum: 10, 		 //用于设置Grid中一次显示的行数，默认值为20。
        rowList: [10,20,30], //一个数组，用于设置Grid可以接受的rowNum值。例如[10,20,30]。 
        recordtext: I18N.displayRecords,//显示记录数的格式
  		emptyrecords: I18N.emptyTable,  //空记录时的提示信息
  		loadtext: I18N.loading, //获得数据时的提示信息
  		//toolbar: [true,"top"],//工具条
  		onSelectRow:function(id){ //用于中状态下设置阴影
		    $('#ctxmenu').css({display:'none'});
		    $('.context-menu-shadow').css({display:'none'});
		}, 
        toppager: true          //按钮栏在表头显示
        //imgpath: 'themes/redmond/images', //图片路径  
        //direction: "ltr"
    }).navGrid('#pageToolBar1',{cloneToTop:true, view:true, viewtext:I18N.view, viewtitle:I18N.view,
    	add:true, addtext:I18N.add, addtitle:I18N.add, //addfunc:addrow,
    	edit:true, edittext:I18N.edit, edittitle:I18N.edit, del:true, deltext:I18N.del, deltitle:I18N.del,
    	search:true, searchtext:I18N.search, searchtitle:I18N.search, refresh:true, refreshtext:I18N.refresh, 
    	refreshtitle:I18N.refresh}).navGridposition();//jQuery("#grid1").jqGrid('navGrid', "#pageToolBar1");
    //在pager上新增"新增"按钮
    jQuery("#grid1").navSeparatorAdd('#pageToolBar1')
    .navButtonAdd('#pageToolBar1',{caption:"新增一条记录", buttonicon:"ui-icon-add", onClickButton: addrow}); 
    var serial=0;
   	function addrow() {
        var newrowdata={}, newid = "newrow" + serial;
	    $("#grid1").jqGrid('addRowData', newid, newrowdata ,'last');
        serial++;
        //jQuery("#grid1").jqGrid('editGridRow',"new",{height:280,reloadAfterSubmit:false});
    };
    //在pager上新增"删除"按钮
    jQuery("#grid1").navButtonAdd('#pageToolBar1',{caption:"删除记录", buttonicon:"ui-icon-add", onClickButton: delrow}); 
    var delRowIdArr = [];
    function delrow(id) {
	    delRowIdArr = id.concat(delRowIdArr);//拷贝数组
	    var curdelrowid = id.concat();
	    for(var i=0;i<curdelrowid.length;i++){
	    	$(that).jqGrid('delRowData',curdelrowid[i]);
	    };
    };
    //
    
    //在toolbar上新增按钮
    $("#t_grid1").append("<td id='add' class='ui-pg-button ui-corner-all'><div><span class='ui-icon ui-icon-newwin'></span><span class='ui-pg-button-text'>新增</span></div></td>"); 
    $("#add","#t_grid1").click(function(){ alert("Hi! I'm added button at this toolbar"); });
     
    /** local date 
     var mydata = [{id:"1",birthDate:"1990-10-01",name:"小红",amount:"200.00",note:"备注1"},  
                   {id:"2",birthDate:"1990-10-02",name:"小明",amount:"300.00",note:"备注2"}];  
     for(var i=0; i<=mydata.length; i++) {  
         jQuery("#grid1").addRowData(i+1, mydata[i]);
     }  
    */
});
/**
    prmNames : {   
        page:"page",    // 表示请求页码的参数名称   
        rows:"rows",    // 表示请求行数的参数名称   
        sort: "sidx", // 表示用于排序的列名的参数名称   
        order: "sord", // 表示采用的排序方式的参数名称   
        search:"_search", // 表示是否是搜索请求的参数名称   
        nd:"nd", // 表示已经发送请求的次数的参数名称   
        id:"id", // 表示当在编辑数据模块中发送数据时，使用的id的名称   
        oper:"oper",    // operation参数名称（我暂时还没用到）   
        editoper:"edit", // 当在edit模式中提交数据时，操作的名称   
        addoper:"add", // 当在add模式中提交数据时，操作的名称   
        deloper:"del", // 当在delete模式中提交数据时，操作的名称   
        subgridid:"id", // 当点击以载入数据到子表时，传递的数据名称   
        npage: null,    
        totalrows:"totalrows" // 表示需从Server得到总共多少行数据的参数名称
    } 
    jsonReader : {   
        root: "rows",   // json中代表实际模型数据的入口   
        page: "page",   // json中代表当前页码的数据   
        total: "total", // json中代表页码总数的数据   
        records: "records", // json中代表数据行总数的数据   
        repeatitems: true, // 如果设为false，则jqGrid在解析json时，会根据name来搜索对应的数据元素（即可以json中元素可以不按顺序）；而所使用的name是,来自于colModel中的name设定。   
        cell: "cell",   //cell中不需要各列的name，只要值就OK了，但是需要保持对应 
        id: "id",   
        userdata: "userdata",   
        subgrid: {   
            root:"rows",    
            repeatitems: true,    
            cell:"cell"  
        }   
    }   
    "rows" : [{"id" :"1", "cell" :["cell11", "cell12", "cell13"]},{"id" :"2", "cell" :["cell21", "cell22", "cell23"]}]
    
    
	属性 	       数据类型 	备注 	默认值
	align 	      string 	定义单元格对齐方式；可选值：left, center, right. 	left
	classes 	  string 	设置列的css。多个class之间用空格分隔，如：'class1 class2' 。表格默认的css属性是ui-ellipsis 	empty string
	datefmt 	  string 	对日期列进行格式化。”/”, ”-”, and ”.”都是有效的日期分隔符。y,Y,yyyy 年YY, yy 月m,mm for monthsd,dd 日. 	ISO Date (Y-m-d)
	defval 	      string 	查询字段的默认值 	空
	editable 	  boolean 	单元格是否可编辑 	false
	editoptions   array 	对列进行编辑时设置的一些属性 	empty array
	editrules 	  array 	对于可编辑单元格的一些额外属性设置 	empty array
	edittype 	  string 	可以编辑的类型。可选值：text, textarea, select, checkbox, password, button, image and file. 	text
	fixed 	      boolean 	列宽度是否要固定不可变 	false
	formoptions   array 	对于form进行编辑时的属性设置 	empty
	formatoptions array 	对某些列进行格式化的设置 	none
	formatter 	  mixed 	对列进行格式化时设置的函数名或者类型 	none
	hidedlg 	  boolean 	是否显示或者隐藏此列 	false
	hidden 	      boolean 	在初始化表格时是否要隐藏此列 	false
	index 	      string 	当排序时定义排序字段名称的索引，参数名为sidx 	empty string
	jsonmap 	  string 	定义了返回的json数据映射 	none
	key 	      boolean 	当从服务器端返回的数据中没有id时，将此作为唯一rowid使用，默认只能有一个id属性 	false
	label 	      string 	如果colNames为空则用此值来作为列的显示名称，如果都没有设置则使用name 值 	none
	name 	      string 	必输项，表格列的名称，所有关键字，保留字都不能作为名称使用包括subgrid, cb and rn. 	Required
	resizable 	  boolean 	是否可以被resizable 	true
	search 	      boolean 	在搜索模式下，定义此列是否可以作为搜索列 	true
	searchoptions array 	设置搜索参数 	empty
	sortable 	  boolean 	是否可排序 	true
	sorttype 	  string 	用在当datatype为local时，定义搜索列的类型，可选值：int/integer - 对integer排序float/number/currency - 排序数字date - 排序日期text - 排序文本 	text
	stype 	      string 	定义搜索元素的类型 	text
	surl 	      string 	搜索数据时的url 	empty string
	width 	      number 	默认列的宽度，只能是象素值，不能是百分比 	150
	xmlmap 	      string 	定义当前列跟返回的xml数据之间的映射关系 	none
	unformat 	  function 	‘unformat’单元格值 	null   
*/

//-->
</script>


	
	