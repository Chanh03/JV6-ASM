const app = angular.module('myApp', []);
app.controller('myCtrl', function ($scope, $http) {
    // CART
    $scope.cart = {
        items: [], add(id) {
            const item = this.items.find(item => item.id === id);
            if (item) {
                item.quantity++;
                this.saveToLocalStorage();
            } else {
                $http.get(`/api/products/${id}`).then(response => {
                    response.data.quantity = 1;
                    this.items.push(response.data);
                    this.saveToLocalStorage();
                });
            }
        }, remove(id) {
            const index = this.items.findIndex(item => item.id === id);
            this.items.splice(index, 1);
            this.saveToLocalStorage();
        }, clear() {
            if (this.items.length === 0) {
                alert('Giỏ hàng trống');
            } else if (confirm('Bạn có chắc chắn muốn xóa tất cả sản phẩm trong giỏ hàng?')) {
                this.items = [];
                this.saveToLocalStorage();
            }
        }, // tổng tiền 1sp
        sumOf(item) {
            return item.price * item.quantity;
        }, get count() {
            const quantities = this.items.map(item => item.quantity);
            const totalQuantity = quantities.reduce((total, quantity) => total + quantity, 0);
            return totalQuantity;
        }, // tổng tiền tất cả
        get sum() {
            const prices = this.items.map(item => item.price * item.quantity);
            const totalPrice = prices.reduce((total, price) => total + price, 0);
            return totalPrice;
        }, saveToLocalStorage() {
            const json = JSON.stringify(this.items);
            localStorage.setItem('cart', json);
        }, loadFromLocalStorage() {
            const json = localStorage.getItem('cart');
            this.items = json ? JSON.parse(json) : [];
        },
    };
    $scope.cart.loadFromLocalStorage();
});