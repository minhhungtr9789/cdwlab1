var product = document.getElementById('products');
// var buttons = product.getElementsByTagName("button");
var btnMinus = product.getElementsByClassName("minus");
var btnPlus = product.getElementsByClassName("plus");
var ipQantity = product.getElementsByClassName('quantity-trash')[0].getElementsByTagName('input');
console.log(btnMinus.length);
for (var i = 0; i < btnMinus.length; i++) {
    btnMinus[i].addEventListener("click", function () {
        var valueOfIp = this.parentElement.getElementsByTagName('input')[0].value;
        console.log(valueOfIp);
        console.log(valueOfIp);
        if (valueOfIp > 1) {
            valueOfIp--;
            this.parentElement.getElementsByTagName('input')[0].value = valueOfIp;
        }
    })
    btnPlus[i].addEventListener("click", function () {
        var valueOfIp = this.parentElement.getElementsByTagName('input')[0].value;
        console.log(valueOfIp);
        console.log(valueOfIp);
        valueOfIp++;
        this.parentElement.getElementsByTagName('input')[0].value = valueOfIp;
    })
}