console.log("memberAdd");

$("#all").click(function (e) {
  let ch = $(this).prop("checked");

  $(".check").prop("checked", ch);
})

$(".check").click(function () {

  //let flag=true;
  $("#all").prop("checked", true);

  $(".check").each(function (idx, item) {
    let ch = $(item).prop("checked")
    if (!ch) {
      //flag=false;
      $("#all").prop("checked", false);
      console.log(ch);
      return false;
    }
  });

  //$("#all").prop("checked", flag);
  //모두 check?? 아니면 하나이상 체크 x??

});

//id, pw, pwEquals, name, email
let results = [false, false, false, false, false]

// ID Check
$("#id").blur(function () {
  console.log("start")
  let id = $("id").val();
  let result = nullCheck($("#id").val(), $("#idResult"), "ID");
  results[0] = result;
  console.log(result)
  //단 id가 비어있지 않을 떄 
  $.get("./idCheck?id="+id, function(data){
    console.log("Data : ", data);
    if(data=='0'){
      $("#idResult").html("사용가능한 ID");
      results[0]=true;
  }else {
      $("#idResult").html("이미 사용중인 ID");
      results[0]=false;
  }

  })

});
// PW Check
// $("#password").blur(function () {
//   let result = nullCheck($("#password").val(), $("#pwResult"), "password");
//   results[1] = result;
// })

// $("#password").change(function(){
//   $("#passwordCheck").val("");
//   results[2] = false;
//   $("#pwcResult").html("");
// });

$("#password").on({
  blur: function () {
    let result = nullCheck($("#password").val(), $("#pwResult"), "password");
    results[1] = result;
  },

  change: function () {
    $("#passwordCheck").val("");
    results[2] = false;
    $("#pwcResult").html("");
  }
});

// PWC Check
$("#passwordCheck").blur(function () {
  let result = equals($("#password").val(), $("#passwordCheck").val());
  if (result) {
    $("#pwcResult").html("정상")
  } else {
    $("#pwcResult").html("PW를 입력하세요");
  }
  results[2] = result;
})
// NAME Check
$("#name").blur(function () {
  let result = nullCheck($("#name").val(), $("#nameResult"), "name");
  results[3] = result;
});
// EMAIL Check
$("#email").blur(function () {
  let result = nullCheck($("#email").val(), $("#emailResult"), "email");
  results[4] = result;
});


$("#addButton").click(function () {

  if (results.includes(false)) {
    alert("필수 입력은 다 입력해라");
  } else {
    alert("전송");
    //$("#addButton").submit();
  }
  // let c = true;
  // $.each(results,function(idx,item){
  //   if(!item){
  //     alert("필수 입력은 다 입력해라");
  //     c = false; 
  //     return c;
  //   }
  // })

  // //event 강제 실행
  // if(c) {
  //   $("#addForm").submit();
  // }
});