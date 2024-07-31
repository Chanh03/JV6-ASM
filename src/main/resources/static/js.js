const app = angular.module('myApp', []);

app.controller('myCtrl', function ($scope, $http) {
    // Giỏ hàng
    $scope.cart = {
        items: [],
        add(id) {
            const item = this.items.find(item => item.id === id);
            if (item) {
                item.quantity++;
                if (item.quantity > 10) {
                    item.quantity = 10;
                    alert(`Số lượng sản phẩm: ${item.name} đã đạt giới hạn`);
                } else {
                    this.saveToLocalStorage();
                }
            } else {
                $http.get(`/api/products/${id}`).then(response => {
                    response.data.quantity = 1;
                    this.items.push(response.data);
                    this.saveToLocalStorage();
                });
            }
        },

        remove(id) {
            const index = this.items.findIndex(item => item.id === id);
            if (index !== -1) {
                this.items.splice(index, 1);
                this.saveToLocalStorage();
            }
        },

        clear() {
            if (this.items.length === 0) {
                alert('Giỏ hàng trống');
            } else if (confirm('Bạn có chắc chắn muốn xóa tất cả sản phẩm trong giỏ hàng?')) {
                this.items = [];
                this.saveToLocalStorage();
            }
        },

        // Tính tổng tiền của một sản phẩm
        sumOf(item) {
            return item.price * item.quantity;
        },

        // Tính tổng số lượng sản phẩm trong giỏ hàng
        get count() {
            if (this.items.length === 0) {
                return '';
            }
            return this.items.reduce((total, item) => total + item.quantity, 0);
        },

        // Tính tổng tiền của tất cả sản phẩm trong giỏ hàng
        get sum() {
            return this.items.reduce((total, item) => total + item.price * item.quantity, 0);
        },

        saveToLocalStorage() {
            localStorage.setItem('cart', JSON.stringify(this.items));
        },

        loadFromLocalStorage() {
            const json = localStorage.getItem('cart');
            this.items = json ? JSON.parse(json) : [];
        },
    };
    $scope.cart.loadFromLocalStorage();
});
