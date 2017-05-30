webpackJsonp([1,4],{

/***/ 100:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(4);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__cart__ = __webpack_require__(178);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__cart_service__ = __webpack_require__(101);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__angular_http__ = __webpack_require__(12);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__product_product_service__ = __webpack_require__(108);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__notifications_shared_service__ = __webpack_require__(19);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return CartComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};







var CartComponent = (function () {
    function CartComponent(changeDetectorRef, http, sharedService) {
        this.changeDetectorRef = changeDetectorRef;
        this.http = http;
        this.sharedService = sharedService;
        this.cart = new __WEBPACK_IMPORTED_MODULE_1__cart__["a" /* Cart */]();
        this.empty = false;
        this.products = {};
        this.cartService = new __WEBPACK_IMPORTED_MODULE_2__cart_service__["a" /* CartService */](this.http);
        this.productService = new __WEBPACK_IMPORTED_MODULE_4__product_product_service__["a" /* ProductService */](this.http);
        this.loading = true;
    }
    CartComponent.prototype.ngOnInit = function () {
        this.init();
    };
    CartComponent.prototype.init = function () {
        var _this = this;
        this.loading = true;
        var id = JSON.parse(localStorage.getItem("customer")).id;
        this.cartService.retrieveCart(id).subscribe(function (response) {
            _this.cart = response.json();
            var tempMap = {};
            response.json().cartElements.forEach(function (element) {
                _this.productService.getProduct(element.productID.valueOf()).subscribe(function (response) {
                    tempMap[element.productID] = response;
                    setTimeout(function () { _this.loading = false; }, 700);
                });
                _this.products = tempMap;
            });
        }, function (error) {
            if (error.status == 404) {
                console.log("ici");
                _this.cart.cartElements = [];
                console.log(_this.cart.cartElements);
                _this.empty = true;
            }
            setTimeout(function () { _this.loading = false; }, 700);
        });
        setTimeout(function () { _this.loading = false; }, 700);
    };
    CartComponent.prototype.updateCart = function (event, element, price) {
        this.cartService.ajouterProduit(element.productID.valueOf(), price, this.update.bind(this));
    };
    CartComponent.prototype.removeElement = function (event, id) {
        var _this = this;
        var customerID = JSON.parse(localStorage.getItem("customer")).id;
        this.cartService.removeElement(id).subscribe(function (Response) {
            _this.sharedService.displayNotification("Produit supprimé avec succès!", true);
            _this.cartService.retrieveCart(customerID).subscribe(function (response) {
                _this.cart = response.json();
            });
            _this.changeDetectorRef.detectChanges();
        });
    };
    CartComponent.prototype.errorImage = function (event) {
        var target = event.target;
        var baseURI = target.baseURI;
        target.src = baseURI + 'assets/notfound.png';
    };
    CartComponent.prototype.update = function () {
        var _this = this;
        var customerID = JSON.parse(localStorage.getItem("customer")).id;
        this.cartService.retrieveCart(customerID).subscribe(function (response) {
            _this.cart = response.json();
            console.log(response.json());
            _this.changeDetectorRef.detectChanges();
            setTimeout(function () { _this.loading = false; }, 700);
        });
        this.sharedService.displayNotification("Produit ajouté avec succès!", true);
    };
    return CartComponent;
}());
CartComponent = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'cart',
        template: __webpack_require__(256),
        styles: [__webpack_require__(244)]
    }),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_0__angular_core__["ChangeDetectorRef"] !== "undefined" && __WEBPACK_IMPORTED_MODULE_0__angular_core__["ChangeDetectorRef"]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_3__angular_http__["Http"] !== "undefined" && __WEBPACK_IMPORTED_MODULE_3__angular_http__["Http"]) === "function" && _b || Object, typeof (_c = typeof __WEBPACK_IMPORTED_MODULE_5__notifications_shared_service__["a" /* SharedService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_5__notifications_shared_service__["a" /* SharedService */]) === "function" && _c || Object])
], CartComponent);

var _a, _b, _c;
//# sourceMappingURL=cart.component.js.map

/***/ }),

/***/ 101:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(4);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_http__ = __webpack_require__(12);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs_Rx__ = __webpack_require__(56);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs_Rx___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_2_rxjs_Rx__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__app_routes__ = __webpack_require__(18);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return CartService; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};




var CartService = (function () {
    function CartService(http) {
        this.http = http;
        this.url = __WEBPACK_IMPORTED_MODULE_3__app_routes__["b" /* gatewayUrl */] + "/carts";
    }
    CartService.prototype.retrieveCart = function (customerID) {
        var empty = false;
        return this.http.get(this.url + "/" + customerID).map(function (response) { return response; }).catch(function (error) { return __WEBPACK_IMPORTED_MODULE_2_rxjs_Rx__["Observable"].throw(error); });
    };
    CartService.prototype.ajouterProduit = function (id, price, callback) {
        var customer = JSON.parse(localStorage['customer']);
        var idCustomer = customer['id'];
        this.retrieveCartAndAdd(id, price, idCustomer, callback);
    };
    CartService.prototype.removeElement = function (id) {
        var customer = localStorage.getItem("customer");
        var customerID = JSON.parse(customer).id;
        return this.http.delete(this.url + "/" + customerID + "/elements/" + id)
            .map(function (response) { response.json(); })
            .catch(function (error) { return __WEBPACK_IMPORTED_MODULE_2_rxjs_Rx__["Observable"].throw("an error occured"); });
    };
    CartService.prototype.retrieveCartAndAdd = function (id, price, idCustomer, callback) {
        var _this = this;
        var observable = this.http.get(this.url + '/' + idCustomer)
            .map(this.extractData)
            .catch(this.handleError);
        observable.subscribe(function (cart) {
            _this.addCart(cart, id, price, idCustomer, callback);
        }, function (error) {
            _this.addCartEmpty(id, price, idCustomer);
        });
    };
    /**
    * ajoute le produit à un panier existant, ou increment son nombre
    */
    CartService.prototype.addCart = function (cart, id, price, idCustomer, callback) {
        var elements = cart.cartElements;
        var contains = false;
        if (elements.length == 0)
            this.addCartEmpty(id, price, idCustomer);
        var elementID = elements.length + 1;
        for (var _i = 0, elements_1 = elements; _i < elements_1.length; _i++) {
            var element = elements_1[_i];
            if (element.productID == id) {
                console.log("called");
                console.log(element.quantity);
                element.quantity++;
                console.log(element.quantity);
                contains = true;
                break;
            }
        }
        if (!contains) {
            var product = {
                elementID: elementID,
                productID: id,
                quantity: 1,
                unitPrice: price
            };
            elements.push(product);
        }
        this.createCart(cart, callback);
    };
    /**
    * créer le panier avec le nouveau produit
    */
    CartService.prototype.addCartEmpty = function (id, price, idCustomer) {
        var data = {
            id: idCustomer,
            cartElements: [{
                    elementID: 1,
                    productID: id,
                    quantity: 1,
                    unitPrice: price
                }],
            timeStamp: new Date().getTime() + '',
            customerID: idCustomer,
            totalPrice: 0.000000
        };
        this.createCart(data, function () { });
    };
    CartService.prototype.createCart = function (data, callback) {
        var headers = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["Headers"]({ 'Content-Type': 'application/json' });
        var options = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["RequestOptions"]({ headers: headers });
        var observable = this.http.post(this.url, data, options)
            .map(this.extractData)
            .catch(this.handleError);
        observable.subscribe(function (resp) {
            console.log(resp);
            callback();
        }, function (error) {
            console.log("unexecpected error");
        });
    };
    CartService.prototype.extractData = function (res) {
        return res.json() || null;
    };
    CartService.prototype.handleError = function (error) {
        return __WEBPACK_IMPORTED_MODULE_2_rxjs_Rx__["Observable"].throw('an error occured');
    };
    return CartService;
}());
CartService = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Injectable"])(),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__angular_http__["Http"] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__angular_http__["Http"]) === "function" && _a || Object])
], CartService);

var _a;
//# sourceMappingURL=cart.service.js.map

/***/ }),

/***/ 102:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(4);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return DescriptionComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};

var DescriptionComponent = (function () {
    function DescriptionComponent() {
    }
    return DescriptionComponent;
}());
DescriptionComponent = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'description',
        template: __webpack_require__(257),
    })
], DescriptionComponent);

//# sourceMappingURL=description.component.js.map

/***/ }),

/***/ 103:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AuthResponse; });
var AuthResponse = (function () {
    function AuthResponse() {
    }
    return AuthResponse;
}());

//# sourceMappingURL=authResponse.js.map

/***/ }),

/***/ 104:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return Credentials; });
var Credentials = (function () {
    function Credentials(email, password) {
        this.email = email;
        this.password = password;
    }
    Credentials.prototype.getEmail = function () {
        return this.email;
    };
    Credentials.prototype.getPassword = function () {
        return this.password;
    };
    Credentials.prototype.setEmail = function (email) {
        this.email = email;
    };
    return Credentials;
}());

//# sourceMappingURL=credentials.js.map

/***/ }),

/***/ 105:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(4);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_http__ = __webpack_require__(12);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__credentials__ = __webpack_require__(104);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__authResponse__ = __webpack_require__(103);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__customer__ = __webpack_require__(180);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__login_service__ = __webpack_require__(67);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__customer_service__ = __webpack_require__(179);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7__authStatus__ = __webpack_require__(66);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_8__angular_router__ = __webpack_require__(33);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_9__notifications_shared_service__ = __webpack_require__(19);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return LoginComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};










var LoginComponent = (function () {
    function LoginComponent(http, router, sharedService, loginService, customerService) {
        this.http = http;
        this.router = router;
        this.sharedService = sharedService;
        this.loginService = loginService;
        this.customerService = customerService;
        this.submitted = false;
        this.credentials = new __WEBPACK_IMPORTED_MODULE_2__credentials__["a" /* Credentials */]("mail2@mail.fr", "passijjfeij");
        this.authResponse = new __WEBPACK_IMPORTED_MODULE_3__authResponse__["a" /* AuthResponse */]();
        this.authStatus = new __WEBPACK_IMPORTED_MODULE_7__authStatus__["a" /* AuthStatus */]();
        this.customer = new __WEBPACK_IMPORTED_MODULE_4__customer__["a" /* Customer */]();
    }
    LoginComponent.prototype.auth = function () {
        var _this = this;
        this.loginService.authenticate(this.credentials, function (error) {
            _this.sharedService.displayNotification('Connexion impossible', false);
        }).subscribe(function (response) {
            _this.authResponse.code = response.json().code;
            _this.authResponse.content = response.json().token;
            if (_this.authResponse != null) {
                localStorage.setItem("token", _this.authResponse.content);
                var token = localStorage.getItem("token");
                _this.customerService.retrieveCustomer(token).subscribe(function (response) {
                    _this.customer.id = response.json().id;
                    _this.customer.firstname = response.json().firstname;
                    _this.customer.lastname = response.json().lastname;
                    _this.customer.email = response.json().email;
                    _this.customer.credentials = response.json().credentials;
                    _this.customer.address = response.json().address;
                    _this.customer.phoneNumber = response.json().phoneNumber;
                    localStorage.setItem("customer", JSON.stringify(_this.customer));
                    _this.router.navigate(['']);
                    _this.sharedService.displayNotification('Vous êtes connecté', true);
                });
            }
        });
    };
    Object.defineProperty(LoginComponent.prototype, "json", {
        get: function () { return JSON.stringify(this.credentials); },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(LoginComponent.prototype, "authResponseJSON", {
        get: function () { return JSON.stringify(this.authResponse); },
        enumerable: true,
        configurable: true
    });
    return LoginComponent;
}());
LoginComponent = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'login',
        styles: [__webpack_require__(245)],
        template: __webpack_require__(258),
        providers: [__WEBPACK_IMPORTED_MODULE_5__login_service__["a" /* LoginService */], __WEBPACK_IMPORTED_MODULE_6__customer_service__["a" /* CustomerService */]]
    }),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__angular_http__["Http"] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__angular_http__["Http"]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_8__angular_router__["b" /* Router */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_8__angular_router__["b" /* Router */]) === "function" && _b || Object, typeof (_c = typeof __WEBPACK_IMPORTED_MODULE_9__notifications_shared_service__["a" /* SharedService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_9__notifications_shared_service__["a" /* SharedService */]) === "function" && _c || Object, typeof (_d = typeof __WEBPACK_IMPORTED_MODULE_5__login_service__["a" /* LoginService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_5__login_service__["a" /* LoginService */]) === "function" && _d || Object, typeof (_e = typeof __WEBPACK_IMPORTED_MODULE_6__customer_service__["a" /* CustomerService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_6__customer_service__["a" /* CustomerService */]) === "function" && _e || Object])
], LoginComponent);

var _a, _b, _c, _d, _e;
//# sourceMappingURL=login.component.js.map

/***/ }),

/***/ 106:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return Message; });
var Message = (function () {
    function Message(message, success, visible) {
        this.message = message;
        this.success = success;
        this.visible = visible;
    }
    Message.prototype.getMessage = function () {
        return this.message;
    };
    Message.prototype.isSuccess = function () {
        return this.success;
    };
    Message.prototype.setMessage = function (message) {
        this.message = message;
    };
    Message.prototype.setSucces = function (succes) {
        this.success = succes;
    };
    Message.prototype.setVisible = function (visible) {
        this.visible = visible;
    };
    Message.prototype.isVisible = function () {
        return this.visible;
    };
    return Message;
}());

//# sourceMappingURL=message.js.map

/***/ }),

/***/ 107:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(4);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_http__ = __webpack_require__(12);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__product_service__ = __webpack_require__(108);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__cart_cart_service__ = __webpack_require__(101);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__angular_router__ = __webpack_require__(33);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return ProductComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};





var ProductComponent = (function () {
    function ProductComponent(http, router, route, productService, cartService) {
        this.http = http;
        this.router = router;
        this.route = route;
        this.productService = productService;
        this.cartService = cartService;
        this.product = null;
        this.show = false;
        this.cartService = new __WEBPACK_IMPORTED_MODULE_3__cart_cart_service__["a" /* CartService */](this.http);
    }
    ProductComponent.prototype.goPreviousPage = function () {
        this.router.navigate(['products']);
    };
    ProductComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.sub = this.route.params.subscribe(function (params) {
            _this.id = +params['id'];
            _this.updateProductInformations();
        });
    };
    ProductComponent.prototype.ngOnDestroy = function () {
        this.sub.unsubscribe();
    };
    ProductComponent.prototype.updateProductInformations = function () {
        var _this = this;
        this.productService.getProduct(this.id)
            .subscribe(function (product) {
            _this.product = product;
        }, function (error) {
            _this.product = null;
        });
    };
    ProductComponent.prototype.ajoutPanier = function (id, price) {
        this.show = true;
        this.cartService.ajouterProduit(id, price, function () { });
    };
    ProductComponent.prototype.errorImage = function (event) {
        var target = event.target;
        var baseURI = target.baseURI;
        target.src = baseURI + 'assets/notfound.png';
    };
    return ProductComponent;
}());
ProductComponent = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'product',
        styles: [__webpack_require__(247)],
        template: __webpack_require__(260),
        providers: [__WEBPACK_IMPORTED_MODULE_2__product_service__["a" /* ProductService */], __WEBPACK_IMPORTED_MODULE_3__cart_cart_service__["a" /* CartService */]]
    }),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__angular_http__["Http"] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__angular_http__["Http"]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_4__angular_router__["b" /* Router */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_4__angular_router__["b" /* Router */]) === "function" && _b || Object, typeof (_c = typeof __WEBPACK_IMPORTED_MODULE_4__angular_router__["c" /* ActivatedRoute */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_4__angular_router__["c" /* ActivatedRoute */]) === "function" && _c || Object, typeof (_d = typeof __WEBPACK_IMPORTED_MODULE_2__product_service__["a" /* ProductService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2__product_service__["a" /* ProductService */]) === "function" && _d || Object, typeof (_e = typeof __WEBPACK_IMPORTED_MODULE_3__cart_cart_service__["a" /* CartService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_3__cart_cart_service__["a" /* CartService */]) === "function" && _e || Object])
], ProductComponent);

var _a, _b, _c, _d, _e;
//# sourceMappingURL=product.component.js.map

/***/ }),

/***/ 108:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(4);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_http__ = __webpack_require__(12);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs_Observable__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs_Observable___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_2_rxjs_Observable__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_rxjs_add_operator_catch__ = __webpack_require__(34);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_rxjs_add_operator_catch___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_3_rxjs_add_operator_catch__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4_rxjs_add_operator_map__ = __webpack_require__(35);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4_rxjs_add_operator_map___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_4_rxjs_add_operator_map__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__app_routes__ = __webpack_require__(18);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return ProductService; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};






var ProductService = (function () {
    function ProductService(http) {
        this.http = http;
        this.productsUrl = __WEBPACK_IMPORTED_MODULE_5__app_routes__["b" /* gatewayUrl */] + '/products';
        this.cartUrl = __WEBPACK_IMPORTED_MODULE_5__app_routes__["b" /* gatewayUrl */] + '/carts';
    }
    ProductService.prototype.getProduct = function (id) {
        var query = this.productsUrl + '/' + id;
        return this.http.get(query)
            .map(this.extractData)
            .catch(this.handleError);
    };
    ProductService.prototype.extractData = function (res) {
        return res.json() || [];
    };
    ProductService.prototype.handleError = function (error) {
        return __WEBPACK_IMPORTED_MODULE_2_rxjs_Observable__["Observable"].throw('an error occured');
    };
    return ProductService;
}());
ProductService = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Injectable"])(),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__angular_http__["Http"] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__angular_http__["Http"]) === "function" && _a || Object])
], ProductService);

var _a;
//# sourceMappingURL=product.service.js.map

/***/ }),

/***/ 109:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(4);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__products_service__ = __webpack_require__(183);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return ProductsComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


var ProductsComponent = (function () {
    function ProductsComponent(productsService) {
        this.productsService = productsService;
        this.maxpages = 1;
        this.productsPerPage = 9;
        this.typingTimer = null;
        this.pages = [];
        this.initPages();
        this.loading = false;
    }
    ProductsComponent.prototype.initPages = function () {
        var _this = this;
        this.productsService
            .getCount()
            .subscribe(function (count) {
            _this.maxpages = Math.floor(count / _this.productsPerPage);
            console.log(_this.productsPerPage, count);
            _this.updatePages();
        }, function (error) {
            _this.updatePages();
            (function (error) { return _this.errorMessage = error; });
        });
    };
    ProductsComponent.prototype.updatePages = function () {
        this.pages = Array(this.maxpages).fill(1).map(function (x, i) { return i + 1; });
    };
    ProductsComponent.prototype.ngOnInit = function () {
        this.updateProducts(1);
    };
    ProductsComponent.prototype.updateProducts = function (page) {
        var _this = this;
        this.actualPage = page;
        this.loading = true;
        this.productsService
            .getProducts(page, this.productsPerPage)
            .subscribe(function (products) {
            _this.products = products;
            _this.loading = false;
        }, function (error) { return _this.errorMessage = error; });
    };
    ProductsComponent.prototype.search = function (event) {
        var _this = this;
        if (event) {
            var self_1 = this;
            clearTimeout(this.typingTimer);
            this.typingTimer = setTimeout(function () {
                self_1.search(null);
            }, 1000);
            return;
        }
        if (!this.criteria || this.criteria == '') {
            this.updateProducts(this.actualPage);
            return;
        }
        this.loading = true;
        this.productsService
            .search(this.criteria)
            .subscribe(function (products) {
            _this.products = products;
            _this.loading = false;
        }, function (error) { return _this.errorMessage = error; });
    };
    ProductsComponent.prototype.getProducts = function () {
        return this.products;
    };
    ProductsComponent.prototype.getActualPage = function () {
        return this.actualPage;
    };
    ProductsComponent.prototype.errorImage = function (event) {
        var target = event.target;
        var baseURI = target.baseURI;
        target.src = baseURI + 'assets/notfound.png';
    };
    return ProductsComponent;
}());
ProductsComponent = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'products',
        styles: [__webpack_require__(249)],
        template: __webpack_require__(262),
        providers: [__WEBPACK_IMPORTED_MODULE_1__products_service__["a" /* ProductsService */]]
    }),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__products_service__["a" /* ProductsService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__products_service__["a" /* ProductsService */]) === "function" && _a || Object])
], ProductsComponent);

var _a;
//# sourceMappingURL=products.component.js.map

/***/ }),

/***/ 110:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(4);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__informations__ = __webpack_require__(184);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__register_service__ = __webpack_require__(185);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__angular_router__ = __webpack_require__(33);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return RegisterComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};




var RegisterComponent = (function () {
    function RegisterComponent(registerService, router) {
        this.registerService = registerService;
        this.router = router;
        this.informations = new __WEBPACK_IMPORTED_MODULE_1__informations__["a" /* Informations */]();
    }
    RegisterComponent.prototype.submit = function () {
        var _this = this;
        if (this.valid()) {
            this.registerService.register(this.informations)
                .subscribe(function (res) {
                localStorage['id'] = res['id'];
                _this.router.navigate(['login']);
            }, function (error) {
                console.log('an error occured');
            });
        }
    };
    RegisterComponent.prototype.valid = function () {
        this.informations.setEmail(this.informations.getEmail());
        return this.informations.areValid();
    };
    return RegisterComponent;
}());
RegisterComponent = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'register',
        styles: [__webpack_require__(250)],
        template: __webpack_require__(263),
        providers: [__WEBPACK_IMPORTED_MODULE_2__register_service__["a" /* RegisterService */]]
    }),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_2__register_service__["a" /* RegisterService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2__register_service__["a" /* RegisterService */]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_3__angular_router__["b" /* Router */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_3__angular_router__["b" /* Router */]) === "function" && _b || Object])
], RegisterComponent);

var _a, _b;
//# sourceMappingURL=register.component.js.map

/***/ }),

/***/ 163:
/***/ (function(module, exports) {

function webpackEmptyContext(req) {
	throw new Error("Cannot find module '" + req + "'.");
}
webpackEmptyContext.keys = function() { return []; };
webpackEmptyContext.resolve = webpackEmptyContext;
module.exports = webpackEmptyContext;
webpackEmptyContext.id = 163;


/***/ }),

/***/ 164:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(4);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_platform_browser_dynamic__ = __webpack_require__(171);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__app_app_module__ = __webpack_require__(175);



if (!/localhost/.test(document.location.host)) {
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["enableProdMode"])();
}
__webpack_require__.i(__WEBPACK_IMPORTED_MODULE_1__angular_platform_browser_dynamic__["a" /* platformBrowserDynamic */])().bootstrapModule(__WEBPACK_IMPORTED_MODULE_2__app_app_module__["a" /* AppModule */]);
//# sourceMappingURL=main.js.map

/***/ }),

/***/ 173:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(4);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_http__ = __webpack_require__(12);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs_Observable__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs_Observable___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_2_rxjs_Observable__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_rxjs_add_operator_catch__ = __webpack_require__(34);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_rxjs_add_operator_catch___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_3_rxjs_add_operator_catch__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4_rxjs_add_operator_map__ = __webpack_require__(35);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4_rxjs_add_operator_map___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_4_rxjs_add_operator_map__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__app_routes__ = __webpack_require__(18);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AccountService; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};






var AccountService = (function () {
    function AccountService(http) {
        this.http = http;
        this.accountUrl = __WEBPACK_IMPORTED_MODULE_5__app_routes__["b" /* gatewayUrl */] + '/customers';
    }
    AccountService.prototype.getInformations = function () {
        var customer = JSON.parse(localStorage['customer']);
        var id = customer['id'];
        var query = this.accountUrl + '/' + id;
        return this.http.get(query)
            .map(this.extractData)
            .catch(this.handleError);
    };
    AccountService.prototype.saveInformations = function (informations, callbackOk, callbackNOk) {
        var headers = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["Headers"]({ 'Content-Type': 'application/json' });
        var options = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["RequestOptions"]({ headers: headers });
        var observable = this.http.put(this.accountUrl, informations, options)
            .map(this.extractData)
            .catch(this.handleError);
        observable.subscribe(function (resp) {
            console.log(resp);
            callbackOk();
        }, function (error) {
            console.log('unexpected error');
            callbackNOk();
        });
    };
    AccountService.prototype.extractData = function (res) {
        return res.json() || [];
    };
    AccountService.prototype.handleError = function (error) {
        return __WEBPACK_IMPORTED_MODULE_2_rxjs_Observable__["Observable"].throw('an error occured');
    };
    return AccountService;
}());
AccountService = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Injectable"])(),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__angular_http__["Http"] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__angular_http__["Http"]) === "function" && _a || Object])
], AccountService);

var _a;
//# sourceMappingURL=account.service.js.map

/***/ }),

/***/ 174:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(4);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__guards_auth_gard__ = __webpack_require__(65);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs_Observable__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs_Observable___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_2_rxjs_Observable__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__angular_router__ = __webpack_require__(33);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__notifications_message__ = __webpack_require__(106);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__notifications_shared_service__ = __webpack_require__(19);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AppComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};






var AppComponent = (function () {
    function AppComponent(authService, router, sharedService) {
        var _this = this;
        this.authService = authService;
        this.router = router;
        this.sharedService = sharedService;
        this.logged = false;
        this.notifVisible = false;
        var ong = localStorage['onglet'];
        this.onglet = ong == null ? 1 : ong;
        this.checkIsConnected();
        this.router.events.subscribe(function (event) {
            if (event instanceof __WEBPACK_IMPORTED_MODULE_3__angular_router__["d" /* NavigationEnd */] && event.url != "/register") {
                _this.checkIsConnected();
            }
        });
    }
    AppComponent.prototype.changeOnglet = function (num) {
        this.onglet = num;
        localStorage['onglet'] = num;
    };
    AppComponent.prototype.ngOnInit = function () {
        this.sharedService.setRootComponent(this);
    };
    AppComponent.prototype.checkIsConnected = function () {
        var _this = this;
        var activate = this.authService.canActivate();
        var isOsb = activate instanceof __WEBPACK_IMPORTED_MODULE_2_rxjs_Observable__["Observable"];
        if (isOsb) {
            activate.subscribe(function (resp) {
                _this.logged = resp;
            }, function (error) {
                _this.logged = false;
            });
        }
    };
    AppComponent.prototype.isLogged = function () {
        return this.logged;
    };
    AppComponent.prototype.disconnect = function () {
        localStorage.clear();
        this.logged = false;
        this.router.navigate(["/login"]);
    };
    AppComponent.prototype.getMessage = function () {
        return this.message;
    };
    AppComponent.prototype.setMessage = function (message, success, visible) {
        this.message = new __WEBPACK_IMPORTED_MODULE_4__notifications_message__["a" /* Message */](message, success, visible);
        this.notifVisible = visible;
    };
    AppComponent.prototype.notificationVisible = function () {
        return this.notifVisible;
    };
    return AppComponent;
}());
AppComponent = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'app-root',
        template: __webpack_require__(253),
        styles: [__webpack_require__(241)],
        providers: [__WEBPACK_IMPORTED_MODULE_1__guards_auth_gard__["a" /* AuthGuard */]]
    }),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__guards_auth_gard__["a" /* AuthGuard */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__guards_auth_gard__["a" /* AuthGuard */]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_3__angular_router__["b" /* Router */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_3__angular_router__["b" /* Router */]) === "function" && _b || Object, typeof (_c = typeof __WEBPACK_IMPORTED_MODULE_5__notifications_shared_service__["a" /* SharedService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_5__notifications_shared_service__["a" /* SharedService */]) === "function" && _c || Object])
], AppComponent);

var _a, _b, _c;
//# sourceMappingURL=app.component.js.map

/***/ }),

/***/ 175:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(4);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_router__ = __webpack_require__(33);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__angular_forms__ = __webpack_require__(170);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__angular_platform_browser__ = __webpack_require__(32);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__angular_platform_browser_animations__ = __webpack_require__(172);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__angular_http__ = __webpack_require__(12);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__app_component__ = __webpack_require__(174);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7__app_routes__ = __webpack_require__(18);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_8__login_login_component__ = __webpack_require__(105);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_9__register_register_component__ = __webpack_require__(110);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_10__products_products_component__ = __webpack_require__(109);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_11__bills_bills_component__ = __webpack_require__(98);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_12__buy_buy_component__ = __webpack_require__(99);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_13__cart_cart_component__ = __webpack_require__(100);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_14__description_description_component__ = __webpack_require__(102);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_15__product_product_component__ = __webpack_require__(107);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_16__product_successfulladd_component__ = __webpack_require__(182);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_17__account_account_component__ = __webpack_require__(97);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_18__notifications_notifications_component__ = __webpack_require__(181);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_19__guards_auth_gard__ = __webpack_require__(65);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_20__notifications_shared_service__ = __webpack_require__(19);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_21__login_login_service__ = __webpack_require__(67);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AppModule; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};






















var AppModule = (function () {
    function AppModule() {
    }
    return AppModule;
}());
AppModule = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["NgModule"])({
        declarations: [
            __WEBPACK_IMPORTED_MODULE_6__app_component__["a" /* AppComponent */],
            __WEBPACK_IMPORTED_MODULE_8__login_login_component__["a" /* LoginComponent */],
            __WEBPACK_IMPORTED_MODULE_9__register_register_component__["a" /* RegisterComponent */],
            __WEBPACK_IMPORTED_MODULE_10__products_products_component__["a" /* ProductsComponent */],
            __WEBPACK_IMPORTED_MODULE_11__bills_bills_component__["a" /* BillsComponent */],
            __WEBPACK_IMPORTED_MODULE_12__buy_buy_component__["a" /* BuyComponent */],
            __WEBPACK_IMPORTED_MODULE_13__cart_cart_component__["a" /* CartComponent */],
            __WEBPACK_IMPORTED_MODULE_14__description_description_component__["a" /* DescriptionComponent */],
            __WEBPACK_IMPORTED_MODULE_16__product_successfulladd_component__["a" /* SuccessfullAddComponent */],
            __WEBPACK_IMPORTED_MODULE_15__product_product_component__["a" /* ProductComponent */],
            __WEBPACK_IMPORTED_MODULE_17__account_account_component__["a" /* AccountComponent */],
            __WEBPACK_IMPORTED_MODULE_18__notifications_notifications_component__["a" /* NotificationsComponent */]
        ],
        imports: [
            __WEBPACK_IMPORTED_MODULE_3__angular_platform_browser__["a" /* BrowserModule */],
            __WEBPACK_IMPORTED_MODULE_2__angular_forms__["a" /* FormsModule */],
            __WEBPACK_IMPORTED_MODULE_2__angular_forms__["b" /* ReactiveFormsModule */],
            __WEBPACK_IMPORTED_MODULE_5__angular_http__["HttpModule"],
            __WEBPACK_IMPORTED_MODULE_4__angular_platform_browser_animations__["a" /* BrowserAnimationsModule */],
            __WEBPACK_IMPORTED_MODULE_1__angular_router__["a" /* RouterModule */].forRoot(__WEBPACK_IMPORTED_MODULE_7__app_routes__["a" /* rootRouterConfig */], { useHash: true })
        ],
        providers: [__WEBPACK_IMPORTED_MODULE_19__guards_auth_gard__["a" /* AuthGuard */], __WEBPACK_IMPORTED_MODULE_20__notifications_shared_service__["a" /* SharedService */], __WEBPACK_IMPORTED_MODULE_21__login_login_service__["a" /* LoginService */]],
        bootstrap: [__WEBPACK_IMPORTED_MODULE_6__app_component__["a" /* AppComponent */]]
    })
], AppModule);

//# sourceMappingURL=app.module.js.map

/***/ }),

/***/ 176:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(4);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_http__ = __webpack_require__(12);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs_Rx__ = __webpack_require__(56);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs_Rx___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_2_rxjs_Rx__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_rxjs_add_operator_catch__ = __webpack_require__(34);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_rxjs_add_operator_catch___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_3_rxjs_add_operator_catch__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4_rxjs_add_operator_map__ = __webpack_require__(35);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4_rxjs_add_operator_map___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_4_rxjs_add_operator_map__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__app_routes__ = __webpack_require__(18);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__notifications_shared_service__ = __webpack_require__(19);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return BillsService; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};







var BillsService = (function () {
    function BillsService(http, sharedService) {
        this.http = http;
        this.sharedService = sharedService;
        this.billsUrl = __WEBPACK_IMPORTED_MODULE_5__app_routes__["b" /* gatewayUrl */] + '/bills/user';
        this.productsUrl = __WEBPACK_IMPORTED_MODULE_5__app_routes__["b" /* gatewayUrl */] + '/products';
    }
    BillsService.prototype.getBills = function () {
        var customer = JSON.parse(localStorage['customer']);
        var id = customer['id'];
        var query = this.billsUrl + '/' + id;
        return this.http.get(query)
            .map(this.extractData)
            .catch(function (error) {
            return __WEBPACK_IMPORTED_MODULE_2_rxjs_Rx__["Observable"].throw(error);
        });
    };
    BillsService.prototype.setImages = function (bills) {
        var _this = this;
        bills.forEach(function (bill) {
            bill.products.forEach(function (product) {
                if (!product.image) {
                    var query = _this.productsUrl + '/' + product.id;
                    _this.getProductInformations(query)
                        .subscribe(function (result) {
                        product.image = result.image;
                    }, function (error) {
                        _this.sharedService.displayNotification('Une errreur est survenue lors du chargement d\'une image', false);
                    });
                }
            });
        });
    };
    BillsService.prototype.getProductInformations = function (query) {
        return this.http.get(query)
            .map(this.extractData)
            .catch(function (error) {
            return __WEBPACK_IMPORTED_MODULE_2_rxjs_Rx__["Observable"].throw(error);
        });
    };
    BillsService.prototype.extractData = function (res) {
        return res.json() || {};
    };
    return BillsService;
}());
BillsService = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Injectable"])(),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__angular_http__["Http"] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__angular_http__["Http"]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_6__notifications_shared_service__["a" /* SharedService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_6__notifications_shared_service__["a" /* SharedService */]) === "function" && _b || Object])
], BillsService);

var _a, _b;
//# sourceMappingURL=bills.service.js.map

/***/ }),

/***/ 177:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(4);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_http__ = __webpack_require__(12);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs_Rx__ = __webpack_require__(56);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs_Rx___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_2_rxjs_Rx__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_rxjs_add_operator_catch__ = __webpack_require__(34);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_rxjs_add_operator_catch___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_3_rxjs_add_operator_catch__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4_rxjs_add_operator_map__ = __webpack_require__(35);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4_rxjs_add_operator_map___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_4_rxjs_add_operator_map__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__app_routes__ = __webpack_require__(18);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return BuyService; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};






var BuyService = (function () {
    function BuyService(http) {
        this.http = http;
        this.buyUrl = __WEBPACK_IMPORTED_MODULE_5__app_routes__["b" /* gatewayUrl */] + '/pay';
    }
    BuyService.prototype.buy = function (callbackOnError) {
        var customer = JSON.parse(localStorage['customer']);
        var id = customer['id'];
        var query = this.buyUrl + '/' + id;
        var headers = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["Headers"]();
        var options = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["RequestOptions"]({ headers: headers });
        return this.http.post(query, headers)
            .map(this.extractData)
            .catch(function (error) {
            callbackOnError();
            return __WEBPACK_IMPORTED_MODULE_2_rxjs_Rx__["Observable"].throw(error);
        });
    };
    BuyService.prototype.extractData = function (res) {
        return res.json() || {};
    };
    return BuyService;
}());
BuyService = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Injectable"])(),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__angular_http__["Http"] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__angular_http__["Http"]) === "function" && _a || Object])
], BuyService);

var _a;
//# sourceMappingURL=buy.service.js.map

/***/ }),

/***/ 178:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return Cart; });
var Cart = (function () {
    function Cart() {
    }
    return Cart;
}());

//# sourceMappingURL=cart.js.map

/***/ }),

/***/ 179:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(4);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_http__ = __webpack_require__(12);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs_Observable__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs_Observable___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_2_rxjs_Observable__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_angular2_jwt__ = __webpack_require__(186);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_angular2_jwt___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_3_angular2_jwt__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__app_routes__ = __webpack_require__(18);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return CustomerService; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};





var CustomerService = (function () {
    function CustomerService(http) {
        this.http = http;
        this.url = __WEBPACK_IMPORTED_MODULE_4__app_routes__["b" /* gatewayUrl */] + '/customers';
        this.jwtHelper = new __WEBPACK_IMPORTED_MODULE_3_angular2_jwt__["JwtHelper"]();
    }
    CustomerService.prototype.retrieveCustomer = function (token) {
        var id = this.jwtHelper.decodeToken(token).customerID;
        return this.http.get(this.url + "/" + id)
            .map(function (response) { return response; })
            .catch(function (error) { return __WEBPACK_IMPORTED_MODULE_2_rxjs_Observable__["Observable"].throw("an error occured"); });
    };
    return CustomerService;
}());
CustomerService = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Injectable"])(),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__angular_http__["Http"] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__angular_http__["Http"]) === "function" && _a || Object])
], CustomerService);

var _a;
//# sourceMappingURL=customer.service.js.map

/***/ }),

/***/ 18:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__login_login_component__ = __webpack_require__(105);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__register_register_component__ = __webpack_require__(110);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__products_products_component__ = __webpack_require__(109);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__bills_bills_component__ = __webpack_require__(98);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__buy_buy_component__ = __webpack_require__(99);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__cart_cart_component__ = __webpack_require__(100);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__description_description_component__ = __webpack_require__(102);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7__guards_auth_gard__ = __webpack_require__(65);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_8__product_product_component__ = __webpack_require__(107);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_9_app_account_account_component__ = __webpack_require__(97);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return rootRouterConfig; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "b", function() { return gatewayUrl; });










var rootRouterConfig = [
    { path: '', redirectTo: 'products', pathMatch: 'full' },
    { path: 'register', component: __WEBPACK_IMPORTED_MODULE_1__register_register_component__["a" /* RegisterComponent */] },
    { path: 'login', component: __WEBPACK_IMPORTED_MODULE_0__login_login_component__["a" /* LoginComponent */] },
    { path: 'products', component: __WEBPACK_IMPORTED_MODULE_2__products_products_component__["a" /* ProductsComponent */], canActivate: [__WEBPACK_IMPORTED_MODULE_7__guards_auth_gard__["a" /* AuthGuard */]] },
    { path: 'cart', component: __WEBPACK_IMPORTED_MODULE_5__cart_cart_component__["a" /* CartComponent */], canActivate: [__WEBPACK_IMPORTED_MODULE_7__guards_auth_gard__["a" /* AuthGuard */]] },
    { path: 'buy', component: __WEBPACK_IMPORTED_MODULE_4__buy_buy_component__["a" /* BuyComponent */], canActivate: [__WEBPACK_IMPORTED_MODULE_7__guards_auth_gard__["a" /* AuthGuard */]] },
    { path: 'description', component: __WEBPACK_IMPORTED_MODULE_6__description_description_component__["a" /* DescriptionComponent */], canActivate: [__WEBPACK_IMPORTED_MODULE_7__guards_auth_gard__["a" /* AuthGuard */]] },
    { path: 'bills', component: __WEBPACK_IMPORTED_MODULE_3__bills_bills_component__["a" /* BillsComponent */], canActivate: [__WEBPACK_IMPORTED_MODULE_7__guards_auth_gard__["a" /* AuthGuard */]] },
    { path: 'product/:id', component: __WEBPACK_IMPORTED_MODULE_8__product_product_component__["a" /* ProductComponent */], canActivate: [__WEBPACK_IMPORTED_MODULE_7__guards_auth_gard__["a" /* AuthGuard */]] },
    { path: 'account', component: __WEBPACK_IMPORTED_MODULE_9_app_account_account_component__["a" /* AccountComponent */], canActivate: [__WEBPACK_IMPORTED_MODULE_7__guards_auth_gard__["a" /* AuthGuard */]] }
];
var gatewayUrl = 'http://10.226.159.191:9090/api/v1';
//# sourceMappingURL=app.routes.js.map

/***/ }),

/***/ 180:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return Customer; });
var Customer = (function () {
    function Customer() {
    }
    return Customer;
}());

//# sourceMappingURL=customer.js.map

/***/ }),

/***/ 181:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(4);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__message__ = __webpack_require__(106);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return NotificationsComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


var NotificationsComponent = (function () {
    function NotificationsComponent() {
        this.state = 'closed';
    }
    NotificationsComponent.prototype.ngOnChanges = function (change) {
        clearInterval(this.interval);
        this.changeState();
    };
    NotificationsComponent.prototype.changeState = function () {
        var _this = this;
        this.state = this.message.isVisible() ? 'open' : 'closed';
        setTimeout(function () {
            _this.message.setVisible(false);
            _this.state = 'closed';
        }, 2000);
    };
    NotificationsComponent.prototype.close = function () {
        this.state = 'closed';
    };
    return NotificationsComponent;
}());
__decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Input"])(),
    __metadata("design:type", typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__message__["a" /* Message */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__message__["a" /* Message */]) === "function" && _a || Object)
], NotificationsComponent.prototype, "message", void 0);
NotificationsComponent = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'notifications',
        styles: [__webpack_require__(246)],
        template: __webpack_require__(259),
        animations: [
            __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["trigger"])("fadeInOut", [
                __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["state"])("open", __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["style"])({ opacity: 1 })),
                __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["state"])("closed", __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["style"])({ opacity: 0 })),
                __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["transition"])("open <=> closed", __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["animate"])("2000ms")),
            ])
        ]
    }),
    __metadata("design:paramtypes", [])
], NotificationsComponent);

var _a;
//# sourceMappingURL=notifications.component.js.map

/***/ }),

/***/ 182:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(4);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__notifications_shared_service__ = __webpack_require__(19);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return SuccessfullAddComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var SuccessfullAddComponent = (function () {
    function SuccessfullAddComponent(sharedService) {
        this.sharedService = sharedService;
        this.show = false;
    }
    SuccessfullAddComponent.prototype.setVisible = function (visible) {
        this.show = visible;
    };
    SuccessfullAddComponent.prototype.setProduct = function (product) {
        this.product = product;
    };
    SuccessfullAddComponent.prototype.close = function () {
        this.show = false;
    };
    SuccessfullAddComponent.prototype.cart = function () {
        this.sharedService.changerOnglet(2);
    };
    return SuccessfullAddComponent;
}());
SuccessfullAddComponent = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'sucessfulladd-component',
        styles: [__webpack_require__(248)],
        template: __webpack_require__(261),
        animations: [
            __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["trigger"])('fadeInOut', [
                __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["transition"])(':enter', [
                    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["style"])({ opacity: 0 }),
                    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["animate"])(500, __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["style"])({ opacity: 1 }))
                ]),
                __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["transition"])(':leave', [
                    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["animate"])(500, __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["style"])({ opacity: 0 }))
                ])
            ])
        ]
    }),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__notifications_shared_service__["a" /* SharedService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__notifications_shared_service__["a" /* SharedService */]) === "function" && _a || Object])
], SuccessfullAddComponent);

var _a;
//# sourceMappingURL=successfulladd.component.js.map

/***/ }),

/***/ 183:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(4);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_http__ = __webpack_require__(12);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs_Observable__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs_Observable___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_2_rxjs_Observable__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_rxjs_add_operator_catch__ = __webpack_require__(34);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_rxjs_add_operator_catch___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_3_rxjs_add_operator_catch__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4_rxjs_add_operator_map__ = __webpack_require__(35);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4_rxjs_add_operator_map___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_4_rxjs_add_operator_map__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__app_routes__ = __webpack_require__(18);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return ProductsService; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};






var ProductsService = (function () {
    function ProductsService(http) {
        this.http = http;
        this.productsUrl = __WEBPACK_IMPORTED_MODULE_5__app_routes__["b" /* gatewayUrl */] + '/products';
    }
    ProductsService.prototype.getProducts = function (page, productsPerPage) {
        var begin = (page * productsPerPage) - 1;
        var end = begin + productsPerPage;
        var query = this.productsUrl + '?range=' + begin + '-' + end;
        return this.http.get(query)
            .map(this.extractData)
            .catch(this.handleError);
    };
    ProductsService.prototype.search = function (criteria) {
        var query = this.productsUrl + '?criterias=' + criteria;
        return this.http.get(query)
            .map(this.extractData)
            .catch(this.handleError);
    };
    ProductsService.prototype.getCount = function () {
        var query = this.productsUrl + '?count=true';
        return this.http.get(query)
            .map(this.extractData)
            .catch(this.handleError);
    };
    ProductsService.prototype.extractData = function (res) {
        return res.json() || [];
    };
    ProductsService.prototype.handleError = function (error) {
        return __WEBPACK_IMPORTED_MODULE_2_rxjs_Observable__["Observable"].throw('an error occured');
    };
    return ProductsService;
}());
ProductsService = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Injectable"])(),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__angular_http__["Http"] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__angular_http__["Http"]) === "function" && _a || Object])
], ProductsService);

var _a;
//# sourceMappingURL=products.service.js.map

/***/ }),

/***/ 184:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__login_credentials__ = __webpack_require__(104);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return Informations; });

var Informations = (function () {
    function Informations() {
        this.credentials = new __WEBPACK_IMPORTED_MODULE_0__login_credentials__["a" /* Credentials */]('', '');
    }
    Informations.prototype.areValid = function () {
        var b = this.isValid(this.firstname) &&
            this.isValid(this.lastname) &&
            this.isValid(this.email) &&
            this.isValid(this.address) &&
            this.isValid(this.phoneNumber) &&
            this.isValidPhone(this.phoneNumber) &&
            this.isValid(this.credentials.getPassword()) &&
            this.isValid(this.credentials.getEmail());
        return b && this.credentials.getEmail() == this.email;
    };
    Informations.prototype.isValid = function (field) {
        return field != null && field.length != 0;
    };
    Informations.prototype.isValidPhone = function (phone) {
        return phone != null && phone.match(/^\d{10,14}$/) != null;
    };
    Informations.prototype.isValidMail = function (mail) {
        return mail != null && mail.match(/\S+@\S+\.\S+/) != null;
    };
    Informations.prototype.setEmail = function (email) {
        this.email = email;
        this.credentials.setEmail(email);
    };
    Informations.prototype.getEmail = function () {
        return this.email;
    };
    return Informations;
}());

//# sourceMappingURL=informations.js.map

/***/ }),

/***/ 185:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(4);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_http__ = __webpack_require__(12);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs_Observable__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs_Observable___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_2_rxjs_Observable__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__app_routes__ = __webpack_require__(18);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return RegisterService; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};




var RegisterService = (function () {
    function RegisterService(http) {
        this.http = http;
        this.url = __WEBPACK_IMPORTED_MODULE_3__app_routes__["b" /* gatewayUrl */] + '/customers';
    }
    RegisterService.prototype.register = function (informations) {
        var body = JSON.stringify(informations);
        var headers = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["Headers"]({ 'content-type': 'application/json' });
        var options = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["RequestOptions"]({ headers: headers });
        return this.http.post(this.url, body, options)
            .map(this.extractData)
            .catch(this.handleError);
    };
    RegisterService.prototype.extractData = function (res) {
        return res.json() || null;
    };
    RegisterService.prototype.handleError = function (error) {
        return __WEBPACK_IMPORTED_MODULE_2_rxjs_Observable__["Observable"].throw('an error occured');
    };
    return RegisterService;
}());
RegisterService = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Injectable"])(),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__angular_http__["Http"] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__angular_http__["Http"]) === "function" && _a || Object])
], RegisterService);

var _a;
//# sourceMappingURL=register.service.js.map

/***/ }),

/***/ 19:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(4);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return SharedService; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};

var SharedService = (function () {
    function SharedService() {
    }
    SharedService.prototype.displayNotification = function (message, success) {
        this.rootComponent.setMessage(message, success, true);
    };
    SharedService.prototype.changerOnglet = function (num) {
        this.rootComponent.changeOnglet(num);
    };
    SharedService.prototype.setRootComponent = function (rootComponent) {
        this.rootComponent = rootComponent;
    };
    return SharedService;
}());
SharedService = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Injectable"])(),
    __metadata("design:paramtypes", [])
], SharedService);

//# sourceMappingURL=shared.service.js.map

/***/ }),

/***/ 240:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(15)(false);
// imports


// module
exports.push([module.i, ".input-group-addon {\r\n  min-width: 110px;\r\n  text-align: left;\r\n  background: #F5F5F5;\r\n}\r\n\r\n.input-group {\r\n  margin-top: 7px;\r\n  border: solid #F5F5F5 1px;\r\n}\r\n\r\n.input-group .form-control {\r\n  padding-left: 7px;\r\n}\r\n", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ 241:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(15)(false);
// imports


// module
exports.push([module.i, ".active {\r\n    background-color: #eeeeee;\r\n}\r\n.footer {\r\n\tbox-shadow : 0px 0px 10px rgba(0,0,0,0.3);\r\n\t-webkit-box-shadow: 0 0px 10px rgba(0,0,0,0.3);\r\n\tborder:none;\r\n\tpadding: 0;\r\n\tmargin: 0;\r\n}\r\n.navbar li a {\r\n\ttext-shadow : 0px 0px 2px rgba(0,0,0,0.2);\r\n\t-webkit-text-shadow: 0 0px 2px rgba(0,0,0,0.2);\r\n}", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ 242:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(15)(false);
// imports


// module
exports.push([module.i, ".bill {\r\n\tbackground-color: #ffffff;\r\n\tborder: solid #DDDDDD 1px;\r\n\tpadding:15px;\r\n}\r\nul li{\r\n\tlist-style-type: none;\r\n}\r\nul {\r\n\tpadding-left: 0;\r\n}", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ 243:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(15)(false);
// imports


// module
exports.push([module.i, ".input-group-addon {\r\n  min-width: 185px;\r\n  text-align: right;\r\n  background-color: #F5F5F5;\r\n}\r\n\r\n.form-control {\r\n  padding-left: 7px;\r\n  border: solid #F5F5F5 1px;\r\n}\r\n\r\n.input-group {\r\n  margin-bottom: 15px;\r\n}\r\n\r\n.glyphicon-chevron-left {\r\n  cursor: pointer;\r\n}\r\n", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ 244:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(15)(false);
// imports


// module
exports.push([module.i, ".buttons {\r\n  text-align: right;\r\n}\r\n\r\nimg {\r\n  width: 50%;\r\n  height: auto;\r\n}\r\n\r\nth {\r\n  border: solid #DDDDDD 1px!important;\r\n}\r\n\r\ntd:first-child {\r\n  border-left: solid #DDDDDD 1px;\r\n}\r\n\r\ntd:last-child {\r\n  border-right: solid #DDDDDD 1px;\r\n}\r\n\r\ntd {\r\n  border-bottom: solid #DDDDDD 1px;\r\n}\r\n\r\nth h4{\r\n\r\n  color :  #fe352d;\r\n}\r\n\r\n.loader {\r\n\r\nwidth :20%;\r\n}\r\n", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ 245:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(15)(false);
// imports


// module
exports.push([module.i, "label:first-child {\r\n  min-width: 109px;\r\n}\r\n\r\nlabel, input {\r\n  border: solid #DDDDDD 1px;\r\n}\r\n\r\ndiv.input-group>input {\r\n  padding-left: 7px;\r\n}\r\n.input-group-addon {\r\n  min-width: 120px!important;\r\n  text-align: left;\r\n  background: #F5F5F5;\r\n}\r\n\r\n.input-group {\r\n  margin-top: 7px;\r\n  border: solid #F5F5F5 1px;\r\n}\r\n\r\n.input-group .form-control {\r\n  padding-left: 7px;\r\n}\r\n", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ 246:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(15)(false);
// imports


// module
exports.push([module.i, "div {\r\n  position: absolute;\r\n  bottom: 0;\r\n  margin-bottom: 0;\r\n  right: 0;\r\n}\r\n", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ 247:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(15)(false);
// imports


// module
exports.push([module.i, ".content {\r\n\tborder : solid 1px #dddddd;\r\n\tpadding: 7px;\r\n}\r\n.glyphicon-chevron-left {\r\n\tposition: absolute;\r\n\tleft: 10px;\r\n\ttop: 10px;\r\n\tcursor: pointer;\r\n}", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ 248:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(15)(false);
// imports


// module
exports.push([module.i, ".modal-dialog {\r\n    position: fixed;\r\n    z-index: 10;\r\n    clear: both;\r\n    right: 20%;\r\n    left: 20%;\r\n    width: 60%;\r\n    top: 20%;\r\n}", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ 249:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(15)(false);
// imports


// module
exports.push([module.i, ".hidden {\r\n\tdisplay: none;\r\n}\r\n.pagination > .active > a {\r\n\tbackground-color: #fe3517;\r\n\tborder-color: #ff2e0f;\r\n}\r\n.pagination>li:not(.active)>a, .pagination>li:not(.active)>span {\r\n\tcolor: #fe3517;\r\n}\r\n.input-group {\r\n\tborder: 1px solid #dddddd;\r\n\tmargin-bottom: 7px;\r\n\tpadding-left: 4px;\r\n}\r\n.input-group-addon {\r\n\tborder-left: 1px solid #dddddd;\r\n\tcursor: pointer;\r\n}\r\n.glyphicon-chevron-right {\r\n    position: absolute;\r\n    right: 10px;\r\n    top: 10px;\r\n    cursor: pointer;\r\n}", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ 250:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(15)(false);
// imports


// module
exports.push([module.i, ".input-group-addon {\r\n  min-width: 120px;\r\n  text-align: left;\r\n  background: #F5F5F5;\r\n}\r\n\r\n.input-group {\r\n  margin-top: 7px;\r\n  border: solid #F5F5F5 1px;\r\n}\r\n\r\n.input-group .form-control {\r\n  padding-left: 7px;\r\n}\r\n", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ 252:
/***/ (function(module, exports) {

module.exports = "<div *ngIf=\"informations != null\" class=\"container\">\r\n\r\n\t<h2 class=\"text-left\">Mon compte</h2>\r\n\r\n\t<div class=\"row\">\r\n\r\n\t\t<div class=\"col-md-3\"></div>\r\n\t\t<form class=\"text-center col-md-6\">\r\n\t\t\t<div class=\"input-group\">\r\n\t\t\t\t<span class=\"input-group-addon\">Nom</span>\r\n\t\t\t\t<input class=\"form-control\" type=\"text\" name=\"lastname\" (input)=\"onChange()\" [(ngModel)]=\"informations.lastname\" />\r\n\t\t\t</div>\r\n\r\n\t\t\t<div class=\"input-group\">\r\n\t\t\t\t<span class=\"input-group-addon\">Prénom</span>\r\n\t\t\t\t<input class=\"form-control\" type=\"text\" name=\"firstname\" (input)=\"onChange()\" [(ngModel)]=\"informations.firstname\" />\r\n\t\t\t</div>\r\n\r\n\t\t\t<div class=\"input-group\">\r\n\t\t\t\t<span class=\"input-group-addon\">Email</span>\r\n\t\t\t\t<input class=\"form-control\" type=\"text\" name=\"email\" (input)=\"onChange()\" [(ngModel)]=\"informations.email\" />\r\n\t\t\t</div>\r\n\r\n\t\t\t<div class=\"input-group\">\r\n\t\t\t\t<span class=\"input-group-addon\">Adresse</span>\r\n\t\t\t\t<input class=\"form-control\" type=\"text\" name=\"address\" (input)=\"onChange()\" [(ngModel)]=\"informations.address\" />\r\n\t\t\t</div>\r\n\r\n\t\t\t<div class=\"input-group\">\r\n\t\t\t\t<span class=\"input-group-addon\">Téléphone</span>\r\n\t\t\t\t<input class=\"form-control\" type=\"text\" name=\"phoneNumber\" (input)=\"onChange()\" [(ngModel)]=\"informations.phoneNumber\" />\r\n\t\t\t</div>\r\n\r\n\t\t\t<br/>\r\n\r\n\t\t\t<div class=\"prog\">\r\n\t\t\t\t<div class=\"progress\" style=\"height: 2px\">\r\n\t\t\t\t\t<div class=\"progress-bar\" [style.width.%]=\"progress\"></div>\r\n\t\t\t\t</div>\r\n\t\t\t</div>\r\n\t\t\t<div class=\"col-md-12\">\r\n\t\t\t\t<div class=\"text-right\">\r\n\t\t\t\t\t<button class=\"btn btn-primary\" [class.disabled]=\"progress!==100\" (click)=\"save()\">Sauvegarder les modifications</button>\r\n\t\t\t\t</div>\r\n\t\t\t</div>\r\n\t\t</form>\r\n\r\n\t\t<div class=\"col-md-3\"></div>\r\n\t</div>\r\n</div>\r\n"

/***/ }),

/***/ 253:
/***/ (function(module, exports) {

module.exports = "<div class=\"allforfooter\">\r\n    <nav class=\"navbar\">\r\n        <div class=\"container-fluid\">\r\n            <div class=\"navbar-header\">\r\n                <button type=\"button\" class=\"navbar-toggle collapsed\" data-toggle=\"collapse\" data-target=\"#bs-navbar-collapse-1\" aria-expanded=\"false\">\r\n                    <span class=\"sr-only\">Toggle navigation</span>\r\n                    <span class=\"icon-bar\"></span>\r\n                    <span class=\"icon-bar\"></span>\r\n                    <span class=\"icon-bar\"></span>\r\n                </button>\r\n                <a href=\"#\" class=\"navbar-left\"><img src=\"assets/sogeti.jpg\" height=\"60px\"/> &nbsp; </a>\r\n                <a class=\"navbar-brand\" [routerLink]=\"['/']\" (click)=\"changeOnglet(1)\">\r\n                    SogiShop\r\n                </a>\r\n            </div>\r\n            <div class=\"collapse navbar-collapse\" id=\"bs-navbar-collapse-1\">\r\n                <ul class=\"nav navbar-nav\">\r\n                    <li [ngClass]=\"{'active':onglet==1}\" (click)=\"changeOnglet(1)\">\r\n                        <a [routerLink]=\"['/products']\">\r\n                            Produits\r\n                            <span class=\"glyphicon glyphicon-th-list\"></span>\r\n                        </a>\r\n                    </li>\r\n                    <li *ngIf=\"logged\" [ngClass]=\"{'active':onglet==2}\" (click)=\"changeOnglet(2)\">\r\n                        <a [routerLink]=\"['/cart']\">\r\n                            Panier\r\n                            <span class=\"glyphicon glyphicon-shopping-cart\"></span>\r\n                        </a>\r\n                    </li>\r\n                    <li *ngIf=\"logged\" [ngClass]=\"{'active':onglet==3}\" (click)=\"changeOnglet(3)\">\r\n                        <a [routerLink]=\"['/account']\">\r\n                            Mon compte\r\n                            <span class=\"glyphicon glyphicon-user\"></span>\r\n                        </a>\r\n                    </li>\r\n\r\n                    <li *ngIf=\"logged\" [ngClass]=\"{'active':onglet==4}\" (click)=\"changeOnglet(4)\">\r\n                        <a [routerLink]=\"['/bills']\">\r\n                            Commandes\r\n                            <span class=\"glyphicon glyphicon-tags\"></span>\r\n                        </a>\r\n                    </li>\r\n                </ul>\r\n\r\n                <ul class=\"nav navbar-nav navbar-right\">\r\n                    <li *ngIf=\"!isLogged()\" (click)=\"changeOnglet(0)\">\r\n                        <a [routerLink]=\"['/login', {logged: '1'}]\">\r\n                            Connexion\r\n                            <span class=\"glyphicon glyphicon-log-in\"></span>\r\n                        </a>\r\n                    </li>\r\n                    <li *ngIf=\"isLogged()\">\r\n                        <a (click)=\"disconnect();changeOnglet(0)\">\r\n                            Déconnexion\r\n                            <span class=\"glyphicon glyphicon-log-out\"></span>\r\n                        </a>\r\n                    </li>\r\n                </ul>\r\n\r\n            </div>\r\n        </div>\r\n    </nav>\r\n\r\n    <main>\r\n  \r\n        <router-outlet></router-outlet>\r\n    </main>\r\n\r\n</div>\r\n\r\n\r\n<footer class=\"footer\">\r\n    <div class=\"container\">\r\n        <p class=\"text-right\">\r\n            © 2017\r\n        </p>\r\n    </div>\r\n</footer>\r\n\r\n<notifications *ngIf=\"notificationVisible()\" [message]=\"getMessage()\"></notifications>\r\n"

/***/ }),

/***/ 254:
/***/ (function(module, exports) {

module.exports = "<div class=\"container\">\r\n\t<div *ngFor=\"let bill of bills\">\r\n\t\t<div class=\"well well-lg\">\r\n\t\t\t<div class=\"row text-center\">\r\n\t\t\t\t<h4>Commande du {{ bill.date }}</h4>\r\n\t\t\t\t<h6>Facture n° {{ bill._id.$oid }}</h6>\r\n\t\t\t</div>\r\n\t\t\t<div class=\"row\">\r\n\t\t\t\t<div class=\"col-md-6 col-xs-6\">\r\n\t\t\t\t\t<h6 class=\"text-center\">Produits</h6>\r\n\t\t\t\t\t<div class=\"row bill\">\r\n\t\t\t\t\t\t<ul>\r\n\t\t\t\t\t\t\t<li *ngFor=\"let product of bill.products\">\r\n\t\t\t\t\t\t\t\t<div class=\"col-md-6\">\r\n\t\t\t\t\t\t\t\t\t<p>Désignation : {{ product.designation }}</p>\r\n\t\t\t\t\t\t\t\t\t<p>Référence : {{ product.reference }}</p>\r\n\t\t\t\t\t\t\t\t\t<p>Prix : {{ product.price }} €</p>\r\n\t\t\t\t\t\t\t\t\t<p>Quantité : {{ product.quantity }}</p>\r\n\t\t\t\t\t\t\t\t\t<p>Total : {{ product.quantity * product.price }} €</p>\r\n\t\t\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t\t\t<div class=\"col-md-6\">\r\n\t\t\t\t\t\t\t\t\t<img src=\"{{ product.image || 'assets//notfound.png' }}\" class=\"img img-responsive thumbnail\" (error)=\"errorImage($event)\"/>\r\n\t\t\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t\t</li>\r\n\t\t\t\t\t\t</ul>\r\n\t\t\t\t\t</div>\r\n\t\t\t\t</div>\r\n\t\t\t\t<div class=\"col-md-6 col-xs-6\">\r\n\t\t\t\t\t<h6 class=\"text-center\">Fournisseur</h6>\r\n\t\t\t\t\t<div class=\"bill\">\r\n\t\t\t\t\t\t<h6 class=\"text-center\">{{ bill.supplier.companyName }}</h6>\r\n\t\t\t\t\t\t<p class=\"text-center\">Contact</p>\r\n\t\t\t\t\t\t<div class=\"col-md-6\">\r\n\t\t\t\t\t\t\t<span>@</span>&nbsp; {{ bill.supplier.email }}\r\n\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t<div class=\"col-md-6\">\r\n\t\t\t\t\t\t\t<span class=\"glyphicon glyphicon-earphone\"></span>&nbsp; {{  bill.supplier.phoneNumber }}\r\n\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t<br/>\r\n\t\t\t\t\t</div>\r\n\t\t\t\t</div>\r\n\t\t\t</div>\r\n\t\t</div>\r\n\t\t<hr/>\r\n\t</div>\r\n</div>"

/***/ }),

/***/ 255:
/***/ (function(module, exports) {

module.exports = "<div *ngIf=\"!paid\" class=\"container\">\r\n\t<div class=\"row\">\r\n\t\t<a [routerLink]=\"['/cart']\">\r\n\t\t\t<span class=\"glyphicon glyphicon-chevron-left\"></span>\r\n\t\t</a>\r\n\t</div>\r\n\t<h2 class=\"text-center\">Paiement</h2>\r\n\t<div class=\"col-md-3\"></div>\r\n\t<div class=\"col-md-6\">\r\n\t\t<form>\r\n\t\t\t<div class=\"input-group\" (input)=\"onChange()\" [class.has-success]=\"isValidCard()\" [class.has-error]=\"!isValidCard() && cardNumber!=null\">\r\n\t\t\t\t<div class=\"input-group-addon\">\r\n\t\t\t\t\tNuméro de la carte &nbsp;\r\n\t\t\t\t\t<span class=\"glyphicon glyphicon-credit-card\"></span>\r\n\t\t\t\t</div>\r\n\t\t\t\t<input class=\"form-control\" type=\"text\" name=\"card\" [(ngModel)]=\"cardNumber\" (input)=\"onChangeCard()\" maxlength=\"19\" />\r\n\t\t\t</div>\r\n\r\n\t\t\t<div class=\"input-group\" (input)=\"onChange()\" [class.has-success]=\"isValidDate()\" [class.has-error]=\"!isValidDate() && expirationDate!=null\">\r\n\t\t\t\t<div class=\"input-group-addon\">\r\n\t\t\t\t\tDate d'éxpiration &nbsp;\r\n\t\t\t\t\t<span class=\"glyphicon glyphicon-calendar\"></span>\r\n\t\t\t\t</div>\r\n\t\t\t\t<input class=\"form-control\" type=\"date\" name=\"expDate\" [(ngModel)]=\"expirationDate\" />\r\n\t\t\t</div>\r\n\r\n\t\t\t<div class=\"input-group\" (input)=\"onChange()\" [class.has-success]=\"isValidCrypto()\" [class.has-error]=\"!isValidCrypto() && cryptogramme\">\r\n\t\t\t\t<div class=\"input-group-addon\">\r\n\t\t\t\t\tCryptogramme visuel &nbsp;\r\n\t\t\t\t\t<span class=\"glyphicon glyphicon-eye-open\"></span>\r\n\t\t\t\t</div>\r\n\t\t\t\t<input class=\"form-control\" type=\"text\" name=\"crypt\" placeholder=\"000\" [(ngModel)]=\"cryptogramme\" />\r\n\t\t\t</div>\r\n\r\n\t\t\t<br/>\r\n\r\n\t\t\t<div class=\"prog\">\r\n\t\t\t\t<div class=\"progress\" style=\"height: 2px\">\r\n\t\t\t\t\t<div class=\"progress-bar\" [style.width.%]=\"progress\"></div>\r\n\t\t\t\t</div>\r\n\t\t\t</div>\r\n\t\t\t<div>\r\n\t\t\t\t<div class=\"text-right\">\r\n\t\t\t\t\t<button class=\"btn btn-primary\" [class.disabled]=\"!isValid()\" (click)=\"buy()\">Payer</button>\r\n\t\t\t\t</div>\r\n\t\t\t</div>\r\n\t\t</form>\r\n\t</div>\r\n\t<div class=\"col-md-3\"></div>\r\n</div>\r\n\r\n<div *ngIf=\"paid\" class=\"container\">\r\n\t<h3>Paiement éffectué avec succès</h3>\r\n\t<div>\r\n\t\t<a [routerLink]=\"['/bills']\" (click)=\"changeActive()\">\r\n\t\t\tConsulter mes commandes\r\n\t\t</a>\r\n\t</div>\r\n</div>"

/***/ }),

/***/ 256:
/***/ (function(module, exports) {

module.exports = "<div class=\"container\">\r\n  <h1>Panier</h1>\r\n  <div class=\"col-md-12\">\r\n    <div *ngIf=\"cart.cartElements != null && cart.cartElements.length > 0\">\r\n\r\n      <table class=\"table table-striped table-hover\" *ngIf=\"!loading\">\r\n        <thead>\r\n          <tr>\r\n            <th>\r\n              <h4>#</h4>\r\n            </th>\r\n            <th class=\"text-center\">\r\n              <h4>Aperçu</h4>\r\n            </th>\r\n            <th>\r\n              <h4>Référence</h4>\r\n            </th>\r\n            <th>\r\n              <h4>Nom</h4>\r\n            </th>\r\n            <th>\r\n              <h4>Prix unitaire</h4>\r\n            </th>\r\n            <th colspan=\"2\">\r\n              <h4 class=\"text-center\">Quantité</h4>\r\n            </th>\r\n          </tr>\r\n        </thead>\r\n        <tr *ngFor=\"let element of cart.cartElements; let rowIndex= index\" >\r\n\r\n          <td>\r\n            {{element.elementID}}\r\n          </td>\r\n          <td *ngIf=\"products[element.productID]\">\r\n            <img src=\"{{products[element.productID].image}}\" class=\"img img-responsive thumbnail center-block\" (error)=\"errorImage($event)\" />\r\n          </td>\r\n          <td *ngIf=\"products[element.productID]\">\r\n            {{products[element.productID].reference}}\r\n          </td>\r\n          <td *ngIf=\"products[element.productID]\">\r\n            {{products[element.productID].designation}}\r\n          </td>\r\n          <td *ngIf=\"products[element.productID]\">\r\n            {{products[element.productID].price}} €\r\n          </td>\r\n          <td>\r\n            <div class=\"col-md-3\">\r\n              {{element.quantity}}\r\n            </div>\r\n            <div class=\"form-group form-inlines col-md-9\">\r\n              <div class=\"buttons\">\r\n                <button class=\"btn btn-primary\" (click)=\"updateCart($event, element, element.unitPrice)\"><span class=\"glyphicon glyphicon-plus\"></span></button>\r\n                <button class=\"btn btn-danger\" (click)=\"removeElement($event, element.elementID)\"><span class=\"glyphicon glyphicon-remove\"></span></button>\r\n              </div>\r\n            </div>\r\n          </td>\r\n        </tr>\r\n        <tr>\r\n          <td>\r\n            <h4>Total</h4>\r\n          </td>\r\n          <td class=\"text-right\" colspan=\"6\">\r\n            <h4>  {{cart.totalPrice}} €</h4>\r\n          </td>\r\n        </tr>\r\n      </table>\r\n      <div class=\"text-right\" *ngIf=\"!loading\">\r\n        <a [class.disabled]=\"empty\" class=\"btn btn-primary\" [routerLink]=\"['/buy']\">\r\n        Acheter &nbsp;\r\n        <span class=\"glyphicon glyphicon-credit-card\"></span>\r\n      </a>\r\n      </div>\r\n    </div>\r\n\r\n  </div>\r\n\r\n  <div *ngIf=\"cart.cartElements != null && cart.cartElements.length == 0\">\r\n\r\n    <table class=\"table table-striped table-hover\" *ngIf=\"!loading\">\r\n\r\n      <tr>\r\n        <th>\r\n          <h4>#</h4>\r\n        </th>\r\n        <th class=\"text-center\">\r\n          <h4>Aperçu</h4>\r\n        </th>\r\n        <th>\r\n          <h4>Référence</h4>\r\n        </th>\r\n        <th>\r\n          <h4>Nom</h4>\r\n        </th>\r\n        <th>\r\n          <h4>Prix unitaire</h4>\r\n        </th>\r\n        <th colspan=\"2\">\r\n          <h4 class=\"text-center\">Quantité</h4>\r\n        </th>\r\n      </tr>\r\n      <tr>\r\n        <td colspan=\"6\">\r\n          <h3 class=\"text-center\">Votre panier est vide</h3>\r\n        </td>\r\n      </tr>\r\n    </table>\r\n  </div>\r\n\r\n</div>\r\n\r\n<div [ngClass]=\"{'hidden' : !loading}\" class=\"text-center container loader\">\r\n  <img src=\"/assets/loader.gif\">\r\n</div>\r\n"

/***/ }),

/***/ 257:
/***/ (function(module, exports) {

module.exports = ""

/***/ }),

/***/ 258:
/***/ (function(module, exports) {

module.exports = "<div class=\"container\">\r\n\t<div class=\"col-md-3\"></div>\r\n\t<div class=\"col-md-6\">\r\n\t\t<h2 class=\"text-center\">Se connecter</h2>\r\n\t\t<form class=\"form-control\">\r\n\t\t\t<div class=\"input-group\">\r\n\t\t\t\t<label class=\"input-group-addon\" for=\"email\">Email</label>\r\n\t\t\t\t<input class=\"form-control\" type=\"text\" name=\"email\" placeholder=\"email\" required [(ngModel)]=\"credentials.email\" />\r\n\t\t\t</div>\r\n\t\t\t<div class=\"input-group\">\r\n\t\t\t\t<label class=\"input-group-addon\" for=\"password\">Mot de passe</label>\r\n\t\t\t\t<input class=\"form-control\" type=\"password\" name=\"password\" placeholder=\"password\" required [(ngModel)]=\"credentials.password\" />\r\n\t\t\t</div>\r\n\t\t\t<br/>\r\n\t\t\t<div class=\"text-right\">\r\n\t\t\t\t<div class=\"col-md-6 text-left\">\r\n\t\t\t\t\tPas inscrit ? <a [routerLink]=\"['/register']\">s'incrire</a>\r\n\t\t\t\t</div>\r\n\t\t\t\t<div class=\"col-md-6\">\r\n\t\t\t\t\t<input type=\"submit\" class=\"btn btn-primary\" (click)=\"auth()\" value=\"Connexion\" />\r\n\t\t\t\t</div>\r\n\t\t\t</div>\r\n\t\t</form>\r\n\t</div>\r\n\t<div class=\"col-md-3\"></div>\r\n</div>\r\n"

/***/ }),

/***/ 259:
/***/ (function(module, exports) {

module.exports = "<div class=\"alert alert-dismissible\" [ngClass]=\"{'alert-success': message.success, 'alert-danger': !message.success}\" [@fadeInOut]=\"state\">\r\n  <button type=\"button\" class=\"close\" data-dismiss=\"alert\" (click)=\"close()\">×</button>\r\n  <strong>{{ message.message }}</strong>\r\n</div>\r\n"

/***/ }),

/***/ 260:
/***/ (function(module, exports) {

module.exports = "<div class=\"container\" *ngIf=\"product\">\r\n\t<div class=\"col-md-2\"></div>\r\n\t<div class=\"col-md-8 content\">\r\n\t\t<a (click)=\"goPreviousPage()\">\r\n\t\t\t<span class=\"glyphicon glyphicon-chevron-left\"></span>\r\n\t\t</a>\r\n\r\n\t\t<h2 class=\"text-center\"> {{ product.designation }} </h2>\r\n\t\t\r\n\t\t<p>\r\n\t\t\tRéférence : {{ product.reference }}\r\n\t\t</p>\r\n\t\t<p>\r\n\t\t\tPrix : {{ product.price }} €\r\n\t\t</p>\r\n\t\t<p>\r\n\t\t\tVendu par <strong> {{ product.supplier.company }} </strong>\r\n\t\t</p>\r\n\r\n\t\t<div class=\"text-center\">\r\n\t\t\t<img src=\"{{ product.image }}\" class=\"img img-responsive center-block thumbnail\" (error)=\"errorImage($event)\"/>\r\n\t\t</div>\r\n\r\n\t\t<div class=\"text-right\">\r\n\t\t\t<button class=\"btn btn-primary\" (click)=\"ajoutPanier(product.id, product.price);comp.setProduct(product);comp.setVisible(true);\">\r\n\t\t\t\tAjouter au panier &nbsp;\r\n\t\t\t\t<span class=\"glyphicon glyphicon-plus\"></span>\r\n\t\t\t</button>\r\n\t\t</div>\r\n\t</div>\r\n\t<div class=\"col-md-2\"></div>\r\n\t<sucessfulladd-component #comp></sucessfulladd-component>\r\n</div>\r\n"

/***/ }),

/***/ 261:
/***/ (function(module, exports) {

module.exports = "<div *ngIf=\"show\" [@fadeInOut]>\r\n\t<div class=\"modal-dialog\" role=\"dialog\">\r\n\t\t<div class=\"modal-content\">\r\n\t\t\t<div class=\"modal-header\">\r\n\t\t\t\t<button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\" (click)=\"close()\">×</button>\r\n\t\t\t\t<h4 class=\"modal-title\">Produit ajouté</h4>\r\n\t\t\t</div>\r\n\r\n\t\t\t<div class=\"modal-body\">\r\n\t\t\t\t<p>Vous vennez d'ajouter le produit {{ product.designation }}, ref {{ product.reference }} à votre panier.</p>\r\n\t\t\t</div>\r\n\r\n\t\t\t<div class=\"modal-footer\">\r\n\t\t\t\t<button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\" (click)=\"close()\">Continuer mes achats</button>\r\n\t\t\t\t<a [routerLink]=\"['/cart']\" (click)=\"cart()\" class=\"btn btn-primary\">Panier</a>\r\n\t\t\t</div>\r\n\t\t</div>\r\n\t</div>\r\n</div>"

/***/ }),

/***/ 262:
/***/ (function(module, exports) {

module.exports = "<div>\r\n\t<div class=\"container\">\r\n\t\t<h1 class=\"text-left\">Produits</h1>\r\n\t\t<div class=\"input-group\">\r\n\t\t\t<input id=\"search\" type=\"text\" name=\"search\" class=\"form-control\" (keyup)=\"search($event);\" [(ngModel)]=\"criteria\">\r\n\t\t\t<span class=\"input-group-addon glyphicon glyphicon-search\" (click)=\"search();\"></span>\r\n\t\t</div>\r\n\t</div>\r\n\t<div class=\"container\" [ngClass]=\"{'hidden' : loading }\">\r\n\t\t<div class=\"list-group\">\r\n\t\t\t<div *ngFor=\"let product of getProducts()\" class=\"list-group-item col-md-4 col-sm-4 col-xs-6\">\r\n\t\t\t\t<a [routerLink]=\"['/product', product.id]\">\r\n\t\t\t\t\t<span class=\"glyphicon glyphicon-chevron-right\"></span>\r\n\t\t\t\t</a>\r\n\t\t\t\t<h5 class=\"text-center\">{{ product.designation }}</h5>\r\n\t\t\t\t<div class=\"col-md-8\">\r\n\t\t\t\t\t<div>Référence : {{ product.reference }}</div>\r\n\t\t\t\t\t<div>Prix : {{ product.price }} €</div>\r\n\t\t\t\t</div>\r\n\t\t\t\t<div class=\"col-md-4\">\r\n\t\t\t\t\t<img src=\"{{ product.image }}\" class=\"img img-responsive\" (error)=\"errorImage($event)\"/>\r\n\t\t\t\t</div>\r\n\t\t\t</div>\r\n\t\t</div>\r\n\t</div>\r\n\t<div class=\"container text-center\" [ngClass]=\"{'hidden' : loading }\">\r\n\t\t<ul class=\"pagination\">\r\n\t\t\t<li *ngFor=\"let i of pages\" [ngClass]=\"{'active' : actualPage==i }\">\r\n\t\t\t\t<a (click)=\"updateProducts(i);\">\r\n\t\t\t\t\t{{ i }}\r\n\t\t\t\t</a>\r\n\t\t\t</li>\r\n\t\t</ul>\r\n\t</div>\r\n</div>\r\n\r\n<div [ngClass]=\"{'hidden' : !loading}\" class=\"text-center container\">\r\n\t<img src=\"/assets/loader.gif\">\r\n</div>"

/***/ }),

/***/ 263:
/***/ (function(module, exports) {

module.exports = "<div class=\"container\">\r\n\t<div class=\"col-md-3\"></div>\r\n\t<form class=\"col-md-6\">\r\n\t\t<h2 class=\"text-center\">S'inscrire</h2>\r\n\t\t<div class=\"input-group\" [class.has-success]=\"informations.isValid(informations.firstname)\">\r\n\t\t\t<label class=\"input-group-addon\" for=\"firstname\">Prénom</label>\r\n\t\t\t<input class=\"form-control\" type=\"text\" name=\"firstname\" [(ngModel)]=\"informations.firstname\"/>\r\n\t\t</div>\r\n\t\t<div class=\"input-group\" [class.has-success]=\"informations.isValid(informations.lastname)\">\r\n\t\t\t<label class=\"input-group-addon\" for=\"lastname\">Nom</label>\r\n\t\t\t<input class=\"form-control\" type=\"text\" name=\"lastname\" [(ngModel)]=\"informations.lastname\"/>\r\n\t\t</div>\r\n\t\t<div class=\"input-group\" \r\n\t\t\t[class.has-success]=\"informations.isValidMail(informations.email)\"\r\n\t\t\t[class.has-error]=\"!informations.isValidMail(informations.email) && informations.email != null\">\r\n\t\t\t<label class=\"input-group-addon\" for=\"email\">Email</label>\r\n\t\t\t<input class=\"form-control\" type=\"text\" name=\"email\" required [(ngModel)]=\"informations.email\"/>\r\n\t\t</div>\r\n\t\t<div class=\"input-group\" [class.has-success]=\"informations.isValid(informations.credentials.password)\">\r\n\t\t\t<label class=\"input-group-addon\" for=\"password\">Mot de passe</label>\r\n\t\t\t<input class=\"form-control\" type=\"password\" name=\"password\" required [(ngModel)]=\"informations.credentials.password\"/>\r\n\t\t</div>\r\n\t\t<div class=\"input-group\" [class.has-success]=\"informations.isValid(informations.address)\">\r\n\t\t\t<label class=\"input-group-addon\" for=\"address\">Adresse</label>\r\n\t\t\t<input class=\"form-control\" type=\"text\" name=\"address\" required [(ngModel)]=\"informations.address\"/>\r\n\t\t</div>\r\n\t\t<div class=\"input-group\" \r\n\t\t\t[class.has-success]=\"informations.isValidPhone(informations.phoneNumber)\" \r\n\t\t\t[class.has-error]=\"!informations.isValidPhone(informations.phoneNumber) && informations.phoneNumber != null\">\r\n\t\t\t<label class=\"input-group-addon\" for=\"phone\">Mobile</label>\r\n\t\t\t<input class=\"form-control\" type=\"text\" name=\"phone\" required [(ngModel)]=\"informations.phoneNumber\"/>\r\n\t\t</div>\r\n\t\t<br/>\r\n\t\t<div class=\"text-right\">\r\n\t\t\t<div class=\"col-md-6 text-left\">\r\n\t\t\t\tDéjà inscrit ? <a [routerLink]=\"['/login']\">se connecter</a>\r\n\t\t\t</div>\r\n\t\t\t<div class=\"col-md-6 text-right\">\r\n\t\t\t\t<button [class.disabled]=\"!valid()\" class=\"btn btn-primary\" (click)=\"submit()\">Valider l'inscription</button>\r\n\t\t\t</div>\r\n\t\t</div>\r\n\t</form>\r\n\t<div class=\"col-md-3\"></div>\r\n</div>"

/***/ }),

/***/ 526:
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__(164);


/***/ }),

/***/ 65:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(4);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_router__ = __webpack_require__(33);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__login_login_service__ = __webpack_require__(67);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__login_authStatus__ = __webpack_require__(66);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__angular_http__ = __webpack_require__(12);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AuthGuard; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};





var AuthGuard = (function () {
    function AuthGuard(router, http, loginService) {
        this.router = router;
        this.http = http;
        this.loginService = loginService;
    }
    AuthGuard.prototype.canActivate = function () {
        var _this = this;
        var authStatus = new __WEBPACK_IMPORTED_MODULE_3__login_authStatus__["a" /* AuthStatus */]();
        if (localStorage.getItem("token") && localStorage.getItem("token") !== "" && localStorage.getItem("token") !== null) {
            return this.loginService.retrieveAuthStatus(localStorage.getItem("token")).map(function (response) {
                authStatus.code = response.json().code;
                authStatus.message = response.json().message;
                console.log(authStatus);
                if (authStatus != null && authStatus.code == 0) {
                    return true;
                }
                else {
                    console.log("not connected, must login token");
                    _this.router.navigate(['login']);
                    return false;
                }
            });
        }
        else {
            console.log("not connected, must login");
            this.router.navigate(['login']);
            return false;
        }
    };
    return AuthGuard;
}());
AuthGuard = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Injectable"])(),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__angular_router__["b" /* Router */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__angular_router__["b" /* Router */]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_4__angular_http__["Http"] !== "undefined" && __WEBPACK_IMPORTED_MODULE_4__angular_http__["Http"]) === "function" && _b || Object, typeof (_c = typeof __WEBPACK_IMPORTED_MODULE_2__login_login_service__["a" /* LoginService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2__login_login_service__["a" /* LoginService */]) === "function" && _c || Object])
], AuthGuard);

var _a, _b, _c;
//# sourceMappingURL=auth.gard.js.map

/***/ }),

/***/ 66:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AuthStatus; });
var AuthStatus = (function () {
    function AuthStatus() {
    }
    return AuthStatus;
}());

//# sourceMappingURL=authStatus.js.map

/***/ }),

/***/ 67:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(4);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_http__ = __webpack_require__(12);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs_Rx__ = __webpack_require__(56);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs_Rx___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_2_rxjs_Rx__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__authResponse__ = __webpack_require__(103);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__authStatus__ = __webpack_require__(66);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return LoginService; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};





var LoginService = (function () {
    function LoginService(http) {
        this.http = http;
        this.authUrl = 'http://10.226.159.191:9090/api/v1/auth';
    }
    LoginService.prototype.authenticate = function (credentials, callbackError) {
        var body = JSON.stringify(credentials);
        var headers = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["Headers"]({ 'content-type': 'application/json' });
        var options = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["RequestOptions"]({ headers: headers });
        var authResponse = new __WEBPACK_IMPORTED_MODULE_3__authResponse__["a" /* AuthResponse */]();
        console.log(this.authUrl);
        return this.http.post(this.authUrl, body, options)
            .map(function (response) { return response; })
            .catch(function (error) {
            callbackError();
            return __WEBPACK_IMPORTED_MODULE_2_rxjs_Rx__["Observable"].throw('an error occured');
        });
    };
    LoginService.prototype.retrieveAuthStatus = function (token) {
        var headers = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["Headers"]({ 'content-type': 'application/json' });
        headers.append('Authorization', 'Bearer ' + token);
        var options = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["RequestOptions"]({ headers: headers });
        var authStatus = new __WEBPACK_IMPORTED_MODULE_4__authStatus__["a" /* AuthStatus */]();
        return this.http.get(this.authUrl, options)
            .map(function (response) { return response; })
            .catch(function (error) { return __WEBPACK_IMPORTED_MODULE_2_rxjs_Rx__["Observable"].throw("an error occured"); });
    };
    return LoginService;
}());
LoginService = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Injectable"])(),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__angular_http__["Http"] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__angular_http__["Http"]) === "function" && _a || Object])
], LoginService);

var _a;
//# sourceMappingURL=login.service.js.map

/***/ }),

/***/ 97:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(4);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__account_service__ = __webpack_require__(173);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__notifications_shared_service__ = __webpack_require__(19);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AccountComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var AccountComponent = (function () {
    function AccountComponent(accountService, sharedService) {
        this.accountService = accountService;
        this.sharedService = sharedService;
        this.fields = 5;
        this.progress = 0;
    }
    AccountComponent.prototype.save = function () {
        var _this = this;
        this.accountService.saveInformations(this.informations, function () {
            _this.sharedService.displayNotification("Informations mises à jour.", true);
        }, function () {
            _this.sharedService.displayNotification("Une erreur est survenue", false);
        });
    };
    AccountComponent.prototype.onChange = function () {
        var valid = this.validQuantity() * 10 * 2;
        this.progress = valid;
    };
    AccountComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.accountService.getInformations()
            .subscribe(function (informations) {
            _this.informations = informations;
            _this.onChange();
        }, function (error) {
            console.log(error);
        });
    };
    AccountComponent.prototype.validQuantity = function () {
        var quantity = 0;
        var info = this.informations;
        if (info == null)
            return 0;
        var fields = [info.lastname, info.firstname, info.email, info.address, info.phoneNumber];
        for (var _i = 0, fields_1 = fields; _i < fields_1.length; _i++) {
            var field = fields_1[_i];
            if (this.isValid(field)) {
                quantity++;
            }
        }
        return quantity;
    };
    AccountComponent.prototype.isValid = function (field) {
        return field != null && field.trim().length > 0;
    };
    return AccountComponent;
}());
AccountComponent = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'account',
        styles: [__webpack_require__(240)],
        template: __webpack_require__(252),
        providers: [__WEBPACK_IMPORTED_MODULE_1__account_service__["a" /* AccountService */]]
    }),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__account_service__["a" /* AccountService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__account_service__["a" /* AccountService */]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_2__notifications_shared_service__["a" /* SharedService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2__notifications_shared_service__["a" /* SharedService */]) === "function" && _b || Object])
], AccountComponent);

var _a, _b;
//# sourceMappingURL=account.component.js.map

/***/ }),

/***/ 98:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(4);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__bills_service__ = __webpack_require__(176);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__notifications_shared_service__ = __webpack_require__(19);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return BillsComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var BillsComponent = (function () {
    function BillsComponent(billsService, sharedService) {
        this.billsService = billsService;
        this.sharedService = sharedService;
        this.bills = [];
    }
    BillsComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.billsService.getBills().subscribe(function (result) {
            _this.bills = result;
            _this.billsService.setImages(_this.bills);
        }, function (error) {
            _this.sharedService.displayNotification('Impossible d\'afficher vos commandes', false);
        });
    };
    BillsComponent.prototype.errorImage = function (event) {
        var target = event.target;
        var baseURI = target.baseURI;
        target.src = baseURI + 'assets/notfound.png';
    };
    return BillsComponent;
}());
BillsComponent = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'bills',
        template: __webpack_require__(254),
        providers: [__WEBPACK_IMPORTED_MODULE_1__bills_service__["a" /* BillsService */]],
        styles: [__webpack_require__(242)]
    }),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__bills_service__["a" /* BillsService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__bills_service__["a" /* BillsService */]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_2__notifications_shared_service__["a" /* SharedService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2__notifications_shared_service__["a" /* SharedService */]) === "function" && _b || Object])
], BillsComponent);

var _a, _b;
//# sourceMappingURL=bills.component.js.map

/***/ }),

/***/ 99:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(4);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__buy_service__ = __webpack_require__(177);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__notifications_shared_service__ = __webpack_require__(19);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return BuyComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var BuyComponent = (function () {
    function BuyComponent(buyService, sharedService) {
        this.buyService = buyService;
        this.sharedService = sharedService;
        this.valid = false;
        this.progress = 0;
        this.paid = false;
    }
    ;
    BuyComponent.prototype.isValid = function () {
        return this.isValidDate() && this.isValidCard() && this.isValidCrypto();
    };
    BuyComponent.prototype.onChange = function () {
        var nbValid = 0;
        for (var _i = 0, _a = [this.isValidCard, this.isValidCrypto, this.isValidDate]; _i < _a.length; _i++) {
            var isValid = _a[_i];
            if (isValid.bind(this)())
                nbValid++;
        }
        this.progress = (nbValid * 10 * 10 / 3);
    };
    BuyComponent.prototype.buy = function () {
        var _this = this;
        if (!this.isValid())
            return;
        this.buyService.buy(function () {
            _this.sharedService.displayNotification('Une erreur est servenue', false);
        }).subscribe(function (result) {
            _this.bills = result;
            _this.paid = true;
        });
    };
    BuyComponent.prototype.isValidCard = function () {
        if (this.cardNumber == null)
            return false;
        var card = this.cardNumber.split('-').join('');
        var validCard = new RegExp("^[0-9]{16}$").test(card);
        return validCard;
    };
    BuyComponent.prototype.isValidCrypto = function () {
        var validCrypt = new RegExp("^[0-9]{3}$").test(this.cryptogramme);
        return validCrypt;
    };
    BuyComponent.prototype.isValidDate = function () {
        var date = new Date(this.expirationDate);
        var validDate = date.toString() != 'Invalid Date';
        return validDate;
    };
    BuyComponent.prototype.onChangeCard = function () {
        var card = this.cardNumber;
        card = card.split('-').join('');
        var newCard = '';
        for (var i = 0; i < Math.ceil((card.length / 4)); i++) {
            var sl = card.slice(i * 4, (i * 4) + 4);
            newCard += sl;
            if (sl.length == 4 && newCard.length <= 16) {
                newCard += '-';
            }
        }
        this.cardNumber = newCard;
    };
    BuyComponent.prototype.changeActive = function () {
        this.sharedService.changerOnglet(4);
    };
    return BuyComponent;
}());
BuyComponent = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'buy',
        template: __webpack_require__(255),
        styles: [__webpack_require__(243)],
        providers: [__WEBPACK_IMPORTED_MODULE_1__buy_service__["a" /* BuyService */]]
    }),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__buy_service__["a" /* BuyService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__buy_service__["a" /* BuyService */]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_2__notifications_shared_service__["a" /* SharedService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2__notifications_shared_service__["a" /* SharedService */]) === "function" && _b || Object])
], BuyComponent);

var _a, _b;
//# sourceMappingURL=buy.component.js.map

/***/ })

},[526]);
//# sourceMappingURL=main.bundle.js.map