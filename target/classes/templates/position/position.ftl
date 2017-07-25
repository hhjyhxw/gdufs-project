<html>
  <head>
  </head>
  <body>
   &nbsp;<br>X:<input   type="text"   id="X"   readonly>   
  Y:<input   type="text"   id="Y"   readonly>   
  <img   src="http://wx2.sinaimg.cn/mw690/635e723dly1fgr9u74s7jj20m80ck75g.jpg"   onclick="showCoordinate(this);">
  </body>
  
   <script   language="javascript">   
    
  function   showCoordinate(obj){   
       var   r   =   getAbsolutePos(obj);   
  var   x   =   document.getElementById("X");   
  var   y   =   document.getElementById("Y");   
    
  x.value   =   event.clientX   -r.x-3;   
  y.value   =   event.clientY   -r.y-3;   
  }   
    
  function   getAbsolutePos(el)   {   
  var   r   =   {   x:   el.offsetLeft,   y:   el.offsetTop   };   
  if   (el.offsetParent)   {   
  var   tmp   =   getAbsolutePos(el.offsetParent);   
  r.x   +=   tmp.x;   
  r.y   +=   tmp.y;   
  }   
  return   r;   
  }   
    
  </script>   
  
</html>