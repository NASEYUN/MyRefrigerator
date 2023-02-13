<?php
header("Content-Type: text/html; charset=UTF-8");
?>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
    new daum.Postcode({
        oncomplete: function(data) {
            if(data.userSelectedType=="R"){ //R인경우 도로명 주소
                window.TestApp.setAddress(data.zonecode, data.roadAddress, data.buildingName);
            }
            else { //J인 경우, 지번 주소
                window.TestApp.setAddress(data.zonecode, data.jibunAddress, data.buildingName);
            }
        }
    }).open();
</script>