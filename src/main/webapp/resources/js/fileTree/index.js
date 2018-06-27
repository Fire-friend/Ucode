;(function (window, undefined) {
  var treeView = tools.$('#treeView');
//  var fileData = getData ();

	  var thisURL = document.URL;  
	  var  getval =thisURL.split('?')[1];  
	  $.ajax({
	  	url : "/codingSoft/code/getCodeDir.do",
	  	type : "post",
	  	data : {
	  		"codeName" : getval
	  	},
	  	success : function(result) {
	  		doMain(result);
	  	}
	  });
 
  function doMain(fileData){
	// 初始化
	  treeView.innerHTML = treeHtml(fileData, -1);

	  // 事件
	  var fileItem = tools.$('.treeNode');
	  var root_icon = tools.$('.icon-control', fileItem[0])[0];

	  root_icon.className = 'icon icon-control icon-minus';

	  tools.each(fileItem, function (item) {
	    filesHandle(item);
	  });

	  function treeHtml(fileData, fileId) {
	    var _html = '';
	    var children = getChildById(fileData, fileId);
	    var hideChild = fileId > 0 ? 'none' : '';

	    _html += '<ul class="'+hideChild+'">';

	    children.forEach(function (item, index) {
	      var level = getLevelById(fileData, item.id);
	      var distance = (level - 1) * 20 + 'px';
	      var hasChild = hasChilds(fileData, item.id);
	      var className = hasChild ? '' : 'treeNode-empty';
	      var treeRoot_cls = fileId === -1 ? 'treeNode-cur' : '';
	      var fileClassName = (item.title).indexOf(".") == -1 ? 'icon icon-file' : 'icon-isfile';
	      
	      _html += `
	        <li>
	          <div class="treeNode ${className} ${treeRoot_cls}" style="padding-left: ${distance}" data-file-id="${item.id}">
	            <i class="icon icon-control icon-add"></i>
	            <i class="${fileClassName}"></i>
	            <span class="title">${item.title}</span>
	          </div>
	          ${treeHtml(fileData, item.id)}
	        </li>`;
	    });

	    _html += '</ul>';

	    return _html;
	  };

	  function filesHandle(item) {
	    tools.addEvent(item, 'click', function () {
	      var treeNode_cur = tools.$('.treeNode-cur')[0];
	      var fileId = item.dataset.fileId;
	      var curElem = document.querySelector('.treeNode[data-file-id="'+fileId+'"]');
	      var hasChild = hasChilds(fileData, fileId);
	      var icon_control = tools.$('.icon-control', item)[0];
	      var openStatus = true;

	      tools.removeClass(treeNode_cur, 'treeNode-cur');
	      tools.addClass(curElem, 'treeNode-cur');

	      if (hasChild) {
	        openStatus = tools.toggleClass(item.nextElementSibling, 'none');

	        if (openStatus) {
	          icon_control.className = 'icon icon-control icon-add';
	        } else {
	          icon_control.className = 'icon icon-control icon-minus';
	        }
	      }else{
	    	  //TODO弹窗
	    	  var currtentCodeName = $(this).find("span:first").text();
	    	  var textContent;
	    	  
	    	  
			  if( currtentCodeName.indexOf(".") != -1){
				  $.ajax({
		    			url : "/codingSoft/file/getFileContent.do",
		    			type : "post",
		    			data : {
		    				"path" : $("#code_path").val(),
		    				"codeName" : $(this).find("span:first").text()
		    			},
		    			success : function(result) {
		    				showCode(result.info);
		    			}
		    		});
			  }
	      }
	    });
	  };
	  
	  function showCode(textContent){
		  layer.open({
			  type: 1,
			  area: ['700px', '400px'],
			  shadeClose: true, //点击遮罩关闭
			  content: '<pre style="height:95%;"><code>'+ textContent +'</code></pre>'
		  });
	  }
  }
  
  
})(window);