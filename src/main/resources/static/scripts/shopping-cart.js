var stockId = document.getElementById('stock-id').innerText;
var amountElement = document.getElementById('amount');

document.getElementById('btn-purchase').addEventListener('click', () => {
    localStorage.removeItem('cartItems');
})

document.getElementById('btn-cart').addEventListener('click', () => {
    let cartItems = localStorage.getItem('cartItems');
    let value;
    if(cartItems === null){
        value = JSONValueOfStock();
    }else{
        value = cartItems + ';' + JSONValueOfStock();
    }

    localStorage.setItem('cartItems', value);
})

const Stock = function (id, amount){
    const ID = id;
    const AMOUNT = amount;

    return {ID, AMOUNT}
}

function JSONValueOfStock(){
    let stock = Stock(stockId, amountElement.value);
    return JSON.stringify(stock);
}