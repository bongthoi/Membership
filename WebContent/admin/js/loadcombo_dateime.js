$(document).ready(function(){

// Do du lieu vao ddl Nam
loadYear(); loadMonth(); loadDay();
$("#ddlNam,#ddlThang").change(function(){
    loadDay();
});

function loadYear()
    {
    // Gan bien Nam thanh tag <select> co id ddlNam
    var Nam = $("#ddlNam");
    // Reset lai kich thuoc cua ddlNam
    // Tranh truong hop bi du lieu rac moi khi goi ham
    Nam.length = 0;
    // Khai bao bien cuc bo dung de chay vong for
    var iNam = 0;
    // Khai bao bien today kieu Date
    var today = new Date();
    // Cho vong for lap tu 1950 cho den nam hien hanh
    for(iNam=1950; iNam<=today.getFullYear(); iNam++)
        {
        // Khai bao va gan bien optNam la kieu du lieu tag <option>
        var optNam = new Option(iNam, iNam);
        $("#ddlNam").append($(optNam));
        
        }
    }
// Load Thang
function loadMonth()
    {
       var Thang = $("#ddlThang");
        Thang.length = 0;
        var iThang=0;
        for(iThang=1; iThang<=12; iThang++)
            {
            var optThang = new Option(iThang, iThang);
             $("#ddlThang").append($(optThang));
            }
    }
// Load Ngay
function loadDay()
    {
    var Ngay = $("#ddlNgay"); 
    Ngay.length = 0;
    // parseInt: chuyen kieu Thang.value tu String sang Int
    var value =  parseInt($("#ddlThang").val());
    // Dat bien SoNgay de lam gia tri cuoi cho dong lap phat sinh ngay
    var SoNgay = 0;
    
    // Thuc hien cac dong lenh sau dua tren viec so sanh gia tri Thang
    switch (value)
        {
        // Truong hop thang 2
        case 2:
        // Lay gia tri Nam dang duoc chon trong ddlNam
            var gtNam = parseInt($("#ddlNam").val());
        // Thuat toan tinh nam nhuan
            if((gtNam%4==0) && ((gtNam%100!=0)||(gtNam%400==0)))
                {
                    // La nam nhuan
                    SoNgay = 28;
                }
            else
                {
                // Khong la nam nhuan
                    SoNgay = 29;
                }
            break;
        // Truong hop cac thang 1, 3, 5, 7, 8, 10, 12
        case 1: case 3: case 5: case 7: case 8: case 10: case 12:
            SoNgay = 31;
             break;
        // Truong hop cac thang 4, 6, 9, 11
        case 4: case 6: case 9: case 11:
            SoNgay = 30;
             break;
        }
    var iNgay=0;
    // Cho vong lap chay tu 1 den SoNgay o tren
        for(iNgay=1; iNgay<=SoNgay; iNgay++)
            {
            var optNgay =new Option(iNgay, iNgay);
               $("#ddlNgay").append($(optNgay));
            }
     
            
    }

});//end ready