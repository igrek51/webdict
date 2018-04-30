webpackJsonp(["main"], {

    /***/ "./src/$$_lazy_route_resource lazy recursive":
    /***/ (function (module, exports) {

        function webpackEmptyAsyncContext(req) {
            // Here Promise.resolve().then() is used instead of new Promise() to prevent
            // uncatched exception popping up in devtools
            return Promise.resolve().then(function () {
                throw new Error("Cannot find module '" + req + "'.");
            });
        }

        webpackEmptyAsyncContext.keys = function () {
            return [];
        };
        webpackEmptyAsyncContext.resolve = webpackEmptyAsyncContext;
        module.exports = webpackEmptyAsyncContext;
        webpackEmptyAsyncContext.id = "./src/$$_lazy_route_resource lazy recursive";

        /***/
    }),

    /***/ "./src/app/addword/add-word.component.css":
    /***/ (function (module, exports) {

        module.exports = ".row-form-add-word {\n  text-align: center;\n  margin-top: 10px;\n}\n\n.row-form-add-word > .input-group-addon {\n  min-width: 8em;\n  text-align: right;\n}\n"

        /***/
    }),

    /***/ "./src/app/addword/add-word.component.html":
    /***/ (function (module, exports) {

        module.exports = "<div class=\"panel panel-primary\">\n  <div class=\"panel-heading\" id=\"dict-word\">Add new word</div>\n  <div class=\"panel-body\">\n\n    <div class=\"input-group row-form-add-word\">\n      <span class=\"input-group-addon\" id=\"basic-addon1\">Word</span>\n      <input class=\"form-control\" placeholder=\"Word\"\n             aria-describedby=\"basic-addon1\" [(ngModel)]=\"wordName\" (keyup.enter)=\"addNewWord()\">\n    </div>\n\n    <div class=\"input-group row-form-add-word\">\n      <span class=\"input-group-addon\" id=\"basic-addon2\">Definition</span>\n      <input class=\"form-control\" placeholder=\"Definition\"\n             aria-describedby=\"basic-addon2\" [(ngModel)]=\"definition\" (keyup.enter)=\"addNewWord()\">\n    </div>\n\n    <div class=\"row-form-add-word\">\n      <button class=\"btn btn-success\" (click)=\"addNewWord()\">\n        <span class=\"glyphicon glyphicon-plus\" aria-hidden=\"true\"></span> Add\n      </button>\n    </div>\n\n  </div>\n</div>\n"

        /***/
    }),

    /***/ "./src/app/addword/add-word.component.ts":
    /***/ (function (module, __webpack_exports__, __webpack_require__) {

        "use strict";
        /* harmony export (binding) */
        __webpack_require__.d(__webpack_exports__, "a", function () {
            return AddWordComponent;
        });
        /* harmony import */
        var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("./node_modules/@angular/core/esm5/core.js");
        /* harmony import */
        var __WEBPACK_IMPORTED_MODULE_1__angular_common_http__ = __webpack_require__("./node_modules/@angular/common/esm5/http.js");
        /* harmony import */
        var __WEBPACK_IMPORTED_MODULE_2__rest_PayloadResponse__ = __webpack_require__("./src/app/rest/PayloadResponse.ts");
        /* harmony import */
        var __WEBPACK_IMPORTED_MODULE_3__alert_alert_service__ = __webpack_require__("./src/app/alert/alert.service.ts");
        /* harmony import */
        var __WEBPACK_IMPORTED_MODULE_4__user_user_data_service__ = __webpack_require__("./src/app/user/user-data.service.ts");
        var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
            var c = arguments.length,
                r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc,
                d;
            if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
            else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
            return c > 3 && r && Object.defineProperty(target, key, r), r;
        };
        var __metadata = (this && this.__metadata) || function (k, v) {
            if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
        };


        var AddWordComponent = /** @class */ (function () {
            function AddWordComponent(http, alertService, userData) {
                this.http = http;
                this.alertService = alertService;
                this.userData = userData;
            }

            AddWordComponent.prototype.ngOnInit = function () {
            };
            AddWordComponent.prototype.addNewWord = function () {
                var _this = this;
                var addWordDTO = {
                    'word': this.wordName,
                    'definition': this.definition,
                    'userId': this.userData.userId,
                    'dictionaryCode': this.userData.dictionaryCode,
                };
                var url = '/api/word';
                return this.http.post(url, addWordDTO).subscribe(function (response) {
                    return _this.addNewWordResult(response);
                }, function (err) {
                    return console.log(err);
                });
            };
            AddWordComponent.prototype.addNewWordResult = function (response) {
                if (__WEBPACK_IMPORTED_MODULE_2__rest_PayloadResponse__["a" /* PayloadResponse */].isOk(response)) {
                    this.alertService.success(response.message);
                }
                else {
                    // error occurred
                    console.log(response.message);
                    this.alertService.error(response.message);
                }
                this.wordName = '';
                this.definition = '';
            };
            AddWordComponent = __decorate([
                Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["n" /* Component */])({
                    selector: 'app-add-word',
                    template: __webpack_require__("./src/app/addword/add-word.component.html"),
                    styles: [__webpack_require__("./src/app/addword/add-word.component.css")]
                }),
                __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1__angular_common_http__["a" /* HttpClient */], __WEBPACK_IMPORTED_MODULE_3__alert_alert_service__["a" /* AlertService */], __WEBPACK_IMPORTED_MODULE_4__user_user_data_service__["a" /* UserDataService */]])
            ], AddWordComponent);
            return AddWordComponent;
        }());


        /***/
    }),

    /***/ "./src/app/alert/alert.service.ts":
    /***/ (function (module, __webpack_exports__, __webpack_require__) {

        "use strict";
        /* harmony export (binding) */
        __webpack_require__.d(__webpack_exports__, "a", function () {
            return AlertService;
        });
        /* harmony import */
        var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("./node_modules/@angular/core/esm5/core.js");
        /* harmony import */
        var __WEBPACK_IMPORTED_MODULE_1_rxjs_Subject__ = __webpack_require__("./node_modules/rxjs/_esm5/Subject.js");
        /* harmony import */
        var __WEBPACK_IMPORTED_MODULE_2__angular_router__ = __webpack_require__("./node_modules/@angular/router/esm5/router.js");
        var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
            var c = arguments.length,
                r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc,
                d;
            if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
            else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
            return c > 3 && r && Object.defineProperty(target, key, r), r;
        };
        var __metadata = (this && this.__metadata) || function (k, v) {
            if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
        };


        var AlertService = /** @class */ (function () {
            function AlertService(router) {
                var _this = this;
                this.router = router;
                this.subject = new __WEBPACK_IMPORTED_MODULE_1_rxjs_Subject__["a" /* Subject */]();
                this.keepAfterRouteChange = false;
                // clear alert messages on route change unless 'keepAfterRouteChange' flag is true
                router.events.subscribe(function (event) {
                    if (event instanceof __WEBPACK_IMPORTED_MODULE_2__angular_router__["b" /* NavigationStart */]) {
                        if (_this.keepAfterRouteChange) {
                            // only keep for a single route change
                            _this.keepAfterRouteChange = false;
                        }
                        else {
                            // clear alert messages
                            _this.clear();
                        }
                    }
                });
            }

            AlertService.prototype.success = function (message) {
                this.alert(message, 'alert-success');
            };
            AlertService.prototype.info = function (message) {
                this.alert(message, 'alert-info');
            };
            AlertService.prototype.warn = function (message) {
                this.alert(message, 'alert-warning');
            };
            AlertService.prototype.error = function (message) {
                this.alert(message, 'alert-danger');
            };
            AlertService.prototype.alert = function (message, type, keepAfterRouteChange) {
                if (keepAfterRouteChange === void 0) {
                    keepAfterRouteChange = false;
                }
                if (!this.keepAfterRouteChange) {
                    this.clear();
                }
                this.keepAfterRouteChange = keepAfterRouteChange;
                this.subject.next({message: message, cssClass: type});
            };
            AlertService.prototype.clear = function () {
                // clear alerts
                this.subject.next();
            };
            AlertService.prototype.getAlerts = function () {
                return this.subject.asObservable();
            };
            AlertService = __decorate([
                Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["A" /* Injectable */])(),
                __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_2__angular_router__["c" /* Router */]])
            ], AlertService);
            return AlertService;
        }());


        /***/
    }),

    /***/ "./src/app/alert/alerts-panel.component.css":
    /***/ (function (module, exports) {

        module.exports = ""

        /***/
    }),

    /***/ "./src/app/alert/alerts-panel.component.html":
    /***/ (function (module, exports) {

        module.exports = "<div *ngFor=\"let alert of alerts\">\n  <div\n    class=\"alert alert-dismissible {{alert?.cssClass}}\"\n    role=\"alert\" (click)=\"hideAlert(alert)\">\n    <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">\n      <span aria-hidden=\"true\">&times;</span></button>\n    {{alert?.message}}\n  </div>\n</div>\n"

        /***/
    }),

    /***/ "./src/app/alert/alerts-panel.component.ts":
    /***/ (function (module, __webpack_exports__, __webpack_require__) {

        "use strict";
        /* harmony export (binding) */
        __webpack_require__.d(__webpack_exports__, "a", function () {
            return AlertsPanelComponent;
        });
        /* harmony import */
        var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("./node_modules/@angular/core/esm5/core.js");
        /* harmony import */
        var __WEBPACK_IMPORTED_MODULE_1__alert_service__ = __webpack_require__("./src/app/alert/alert.service.ts");
        var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
            var c = arguments.length,
                r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc,
                d;
            if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
            else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
            return c > 3 && r && Object.defineProperty(target, key, r), r;
        };
        var __metadata = (this && this.__metadata) || function (k, v) {
            if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
        };


        var AlertsPanelComponent = /** @class */ (function () {
            function AlertsPanelComponent(alertService) {
                this.alertService = alertService;
                this.alerts = [];
            }

            AlertsPanelComponent.prototype.ngOnInit = function () {
                var _this = this;
                this.alertService.getAlerts().subscribe(function (alert) {
                    if (!alert) {
                        // clear alerts when an empty alert is received
                        _this.alerts = [];
                        return;
                    }
                    _this.alerts.push(alert);
                    console.log(alert.message);
                });
            };
            AlertsPanelComponent.prototype.hideAlert = function (alert) {
                this.alerts = this.alerts.filter(function (a) {
                    return a !== alert;
                });
            };
            AlertsPanelComponent = __decorate([
                Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["n" /* Component */])({
                    selector: 'app-alerts-panel',
                    template: __webpack_require__("./src/app/alert/alerts-panel.component.html"),
                    styles: [__webpack_require__("./src/app/alert/alerts-panel.component.css")]
                }),
                __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1__alert_service__["a" /* AlertService */]])
            ], AlertsPanelComponent);
            return AlertsPanelComponent;
        }());


        /***/
    }),

    /***/ "./src/app/app.component.css":
    /***/ (function (module, exports) {

        module.exports = ""

        /***/
    }),

    /***/ "./src/app/app.component.html":
    /***/ (function (module, exports) {

        module.exports = "<app-navbar></app-navbar>\n\n<div class=\"container panel panel-default\" id=\"main-container\">\n  <div class=\"panel-body\">\n\n    <app-alerts-panel></app-alerts-panel>\n\n    <router-outlet></router-outlet>\n\n  </div>\n</div>\n\n<app-footer></app-footer>\n"

        /***/
    }),

    /***/ "./src/app/app.component.ts":
    /***/ (function (module, __webpack_exports__, __webpack_require__) {

        "use strict";
        /* harmony export (binding) */
        __webpack_require__.d(__webpack_exports__, "a", function () {
            return AppComponent;
        });
        /* harmony import */
        var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("./node_modules/@angular/core/esm5/core.js");
        var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
            var c = arguments.length,
                r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc,
                d;
            if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
            else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
            return c > 3 && r && Object.defineProperty(target, key, r), r;
        };

        var AppComponent = /** @class */ (function () {
            function AppComponent() {
                this.title = 'app';
            }

            AppComponent = __decorate([
                Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["n" /* Component */])({
                    selector: 'app-root',
                    template: __webpack_require__("./src/app/app.component.html"),
                    styles: [__webpack_require__("./src/app/app.component.css")]
                })
            ], AppComponent);
            return AppComponent;
        }());


        /***/
    }),

    /***/ "./src/app/app.module.ts":
    /***/ (function (module, __webpack_exports__, __webpack_require__) {

        "use strict";
        /* harmony export (binding) */
        __webpack_require__.d(__webpack_exports__, "a", function () {
            return AppModule;
        });
        /* harmony import */
        var __WEBPACK_IMPORTED_MODULE_0__angular_platform_browser__ = __webpack_require__("./node_modules/@angular/platform-browser/esm5/platform-browser.js");
        /* harmony import */
        var __WEBPACK_IMPORTED_MODULE_1__angular_core__ = __webpack_require__("./node_modules/@angular/core/esm5/core.js");
        /* harmony import */
        var __WEBPACK_IMPORTED_MODULE_2__angular_forms__ = __webpack_require__("./node_modules/@angular/forms/esm5/forms.js");
        /* harmony import */
        var __WEBPACK_IMPORTED_MODULE_3__app_component__ = __webpack_require__("./src/app/app.component.ts");
        /* harmony import */
        var __WEBPACK_IMPORTED_MODULE_4__errors_page_not_found_component__ = __webpack_require__("./src/app/errors/page-not-found.component.ts");
        /* harmony import */
        var __WEBPACK_IMPORTED_MODULE_5__routing_app_routing_module__ = __webpack_require__("./src/app/routing/app-routing.module.ts");
        /* harmony import */
        var __WEBPACK_IMPORTED_MODULE_6__top_top_word_component__ = __webpack_require__("./src/app/top/top-word.component.ts");
        /* harmony import */
        var __WEBPACK_IMPORTED_MODULE_7__addword_add_word_component__ = __webpack_require__("./src/app/addword/add-word.component.ts");
        /* harmony import */
        var __WEBPACK_IMPORTED_MODULE_8__settings_settings_component__ = __webpack_require__("./src/app/settings/settings.component.ts");
        /* harmony import */
        var __WEBPACK_IMPORTED_MODULE_9__stats_statistics_component__ = __webpack_require__("./src/app/stats/statistics.component.ts");
        /* harmony import */
        var __WEBPACK_IMPORTED_MODULE_10__wordslist_words_list_component__ = __webpack_require__("./src/app/wordslist/words-list.component.ts");
        /* harmony import */
        var __WEBPACK_IMPORTED_MODULE_11__footer_footer_component__ = __webpack_require__("./src/app/footer/footer.component.ts");
        /* harmony import */
        var __WEBPACK_IMPORTED_MODULE_12__navbar_navbar_component__ = __webpack_require__("./src/app/navbar/navbar.component.ts");
        /* harmony import */
        var __WEBPACK_IMPORTED_MODULE_13__alert_alerts_panel_component__ = __webpack_require__("./src/app/alert/alerts-panel.component.ts");
        /* harmony import */
        var __WEBPACK_IMPORTED_MODULE_14__angular_common_http__ = __webpack_require__("./node_modules/@angular/common/esm5/http.js");
        /* harmony import */
        var __WEBPACK_IMPORTED_MODULE_15__alert_alert_service__ = __webpack_require__("./src/app/alert/alert.service.ts");
        /* harmony import */
        var __WEBPACK_IMPORTED_MODULE_16__wordrank_word_rank_service__ = __webpack_require__("./src/app/wordrank/word-rank.service.ts");
        /* harmony import */
        var __WEBPACK_IMPORTED_MODULE_17__user_user_data_service__ = __webpack_require__("./src/app/user/user-data.service.ts");
        /* harmony import */
        var __WEBPACK_IMPORTED_MODULE_18__routing_auth_guard_service__ = __webpack_require__("./src/app/routing/auth-guard.service.ts");
        var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
            var c = arguments.length,
                r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc,
                d;
            if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
            else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
            return c > 3 && r && Object.defineProperty(target, key, r), r;
        };


        var AppModule = /** @class */ (function () {
            function AppModule() {
            }

            AppModule = __decorate([
                Object(__WEBPACK_IMPORTED_MODULE_1__angular_core__["I" /* NgModule */])({
                    declarations: [
                        __WEBPACK_IMPORTED_MODULE_3__app_component__["a" /* AppComponent */],
                        __WEBPACK_IMPORTED_MODULE_4__errors_page_not_found_component__["a" /* PageNotFoundComponent */],
                        __WEBPACK_IMPORTED_MODULE_6__top_top_word_component__["a" /* TopWordComponent */],
                        __WEBPACK_IMPORTED_MODULE_7__addword_add_word_component__["a" /* AddWordComponent */],
                        __WEBPACK_IMPORTED_MODULE_8__settings_settings_component__["a" /* SettingsComponent */],
                        __WEBPACK_IMPORTED_MODULE_9__stats_statistics_component__["a" /* StatisticsComponent */],
                        __WEBPACK_IMPORTED_MODULE_10__wordslist_words_list_component__["a" /* WordsListComponent */],
                        __WEBPACK_IMPORTED_MODULE_11__footer_footer_component__["a" /* FooterComponent */],
                        __WEBPACK_IMPORTED_MODULE_12__navbar_navbar_component__["a" /* NavbarComponent */],
                        __WEBPACK_IMPORTED_MODULE_13__alert_alerts_panel_component__["a" /* AlertsPanelComponent */]
                    ],
                    imports: [
                        __WEBPACK_IMPORTED_MODULE_0__angular_platform_browser__["a" /* BrowserModule */],
                        __WEBPACK_IMPORTED_MODULE_2__angular_forms__["a" /* FormsModule */],
                        __WEBPACK_IMPORTED_MODULE_5__routing_app_routing_module__["a" /* AppRoutingModule */],
                        __WEBPACK_IMPORTED_MODULE_14__angular_common_http__["b" /* HttpClientModule */]
                    ],
                    providers: [
                        __WEBPACK_IMPORTED_MODULE_15__alert_alert_service__["a" /* AlertService */],
                        __WEBPACK_IMPORTED_MODULE_16__wordrank_word_rank_service__["a" /* WordRankService */],
                        __WEBPACK_IMPORTED_MODULE_17__user_user_data_service__["a" /* UserDataService */],
                        __WEBPACK_IMPORTED_MODULE_18__routing_auth_guard_service__["a" /* AuthGuardService */]
                    ],
                    bootstrap: [__WEBPACK_IMPORTED_MODULE_3__app_component__["a" /* AppComponent */]]
                })
            ], AppModule);
            return AppModule;
        }());


        /***/
    }),

    /***/ "./src/app/errors/page-not-found.component.css":
    /***/ (function (module, exports) {

        module.exports = ""

        /***/
    }),

    /***/ "./src/app/errors/page-not-found.component.html":
    /***/ (function (module, exports) {

        module.exports = "<p>\n  page not found\n</p>\n"

        /***/
    }),

    /***/ "./src/app/errors/page-not-found.component.ts":
    /***/ (function (module, __webpack_exports__, __webpack_require__) {

        "use strict";
        /* harmony export (binding) */
        __webpack_require__.d(__webpack_exports__, "a", function () {
            return PageNotFoundComponent;
        });
        /* harmony import */
        var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("./node_modules/@angular/core/esm5/core.js");
        var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
            var c = arguments.length,
                r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc,
                d;
            if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
            else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
            return c > 3 && r && Object.defineProperty(target, key, r), r;
        };
        var __metadata = (this && this.__metadata) || function (k, v) {
            if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
        };

        var PageNotFoundComponent = /** @class */ (function () {
            function PageNotFoundComponent() {
            }

            PageNotFoundComponent.prototype.ngOnInit = function () {
            };
            PageNotFoundComponent = __decorate([
                Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["n" /* Component */])({
                    selector: 'app-page-not-found',
                    template: __webpack_require__("./src/app/errors/page-not-found.component.html"),
                    styles: [__webpack_require__("./src/app/errors/page-not-found.component.css")]
                }),
                __metadata("design:paramtypes", [])
            ], PageNotFoundComponent);
            return PageNotFoundComponent;
        }());


        /***/
    }),

    /***/ "./src/app/footer/footer.component.css":
    /***/ (function (module, exports) {

        module.exports = ".footer {\n  position: absolute;\n  bottom: 0;\n  width: 100%;\n  /* Set the fixed height of the footer here */\n  height: 40px;\n  line-height: 40px; /* Vertically center the text there */\n  background-color: #f5f5f5;\n  padding: 0 10px;\n  text-align: center;\n}\n"

        /***/
    }),

    /***/ "./src/app/footer/footer.component.html":
    /***/ (function (module, exports) {

        module.exports = "<footer class=\"footer\">\n  <span\n    class=\"text-muted\">{{environmentInfo?.environmentName}} v{{environmentInfo?.buildVersion}}</span>\n</footer>\n"

        /***/
    }),

    /***/ "./src/app/footer/footer.component.ts":
    /***/ (function (module, __webpack_exports__, __webpack_require__) {

        "use strict";
        /* harmony export (binding) */
        __webpack_require__.d(__webpack_exports__, "a", function () {
            return FooterComponent;
        });
        /* harmony import */
        var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("./node_modules/@angular/core/esm5/core.js");
        /* harmony import */
        var __WEBPACK_IMPORTED_MODULE_1__angular_common_http__ = __webpack_require__("./node_modules/@angular/common/esm5/http.js");
        var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
            var c = arguments.length,
                r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc,
                d;
            if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
            else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
            return c > 3 && r && Object.defineProperty(target, key, r), r;
        };
        var __metadata = (this && this.__metadata) || function (k, v) {
            if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
        };


        var infoUrl = '/api/info';
        var FooterComponent = /** @class */ (function () {
            function FooterComponent(http) {
                this.http = http;
            }

            FooterComponent.prototype.ngOnInit = function () {
                var _this = this;
                this.http.get(infoUrl).subscribe(function (data) {
                    return _this.environmentInfo = data;
                }, function (err) {
                    return console.log(err);
                });
            };
            FooterComponent = __decorate([
                Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["n" /* Component */])({
                    selector: 'app-footer',
                    template: __webpack_require__("./src/app/footer/footer.component.html"),
                    styles: [__webpack_require__("./src/app/footer/footer.component.css")]
                }),
                __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1__angular_common_http__["a" /* HttpClient */]])
            ], FooterComponent);
            return FooterComponent;
        }());


        /***/
    }),

    /***/ "./src/app/navbar/navbar.component.css":
    /***/ (function (module, exports) {

        module.exports = ""

        /***/
    }),

    /***/ "./src/app/navbar/navbar.component.html":
    /***/ (function (module, exports) {

        module.exports = "<nav class=\"navbar navbar-inverse bg-inverse no-rounding\">\n  <div class=\"container\">\n    <div class=\"navbar-header\">\n      <button type=\"button\" class=\"navbar-toggle collapsed\" data-toggle=\"collapse\"\n              data-target=\"#navbar\" aria-expanded=\"false\" aria-controls=\"navbar\">\n        <span class=\"sr-only\">Toggle navigation</span>\n        <span class=\"icon-bar\"></span>\n        <span class=\"icon-bar\"></span>\n        <span class=\"icon-bar\"></span>\n      </button>\n      <a class=\"navbar-brand\" routerLink=\"/\">WebDict</a>\n    </div>\n    <div id=\"navbar\" class=\"collapse navbar-collapse\">\n      <ul class=\"nav navbar-nav\">\n        <li routerLinkActive=\"active\"><a\n          routerLink=\"/settings\">Settings</a>\n        </li>\n        <li routerLinkActive=\"active\"><a\n          routerLink=\"/top\">Top</a>\n        </li>\n        <li routerLinkActive=\"active\"><a\n          routerLink=\"/list\">All</a>\n        </li>\n        <li routerLinkActive=\"active\"><a\n          routerLink=\"/add\">Add</a>\n        </li>\n        <li routerLinkActive=\"active\"><a\n          routerLink=\"/stats\">Statistics</a>\n        </li>\n      </ul>\n\n      <ul class=\"nav navbar-nav navbar-right\"\n          *ngIf=\"username != null && dictionaryName != null\">\n        <li>\n          <a routerLink=\"/settings\">\n            <span class=\"glyphicon glyphicon-user\"></span>\n            {{username}}\n          </a>\n        </li>\n        <li>\n          <a routerLink=\"/settings\">\n            <span class=\"glyphicon glyphicon-book\"></span>\n            {{dictionaryName}}\n          </a>\n        </li>\n        <li>\n          <a (click)=\"logOut()\" data-toggle=\"tooltip\" title=\"Log out\">\n            <span class=\"glyphicon glyphicon-log-out\"></span>\n          </a>\n        </li>\n      </ul>\n    </div>\n  </div>\n</nav>\n"

        /***/
    }),

    /***/ "./src/app/navbar/navbar.component.ts":
    /***/ (function (module, __webpack_exports__, __webpack_require__) {

        "use strict";
        /* harmony export (binding) */
        __webpack_require__.d(__webpack_exports__, "a", function () {
            return NavbarComponent;
        });
        /* harmony import */
        var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("./node_modules/@angular/core/esm5/core.js");
        /* harmony import */
        var __WEBPACK_IMPORTED_MODULE_1__user_user_data_service__ = __webpack_require__("./src/app/user/user-data.service.ts");
        var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
            var c = arguments.length,
                r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc,
                d;
            if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
            else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
            return c > 3 && r && Object.defineProperty(target, key, r), r;
        };
        var __metadata = (this && this.__metadata) || function (k, v) {
            if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
        };


// declare var $: any;
        var NavbarComponent = /** @class */ (function () {
            function NavbarComponent(userData) {
                this.userData = userData;
            }

            NavbarComponent.prototype.ngOnInit = function () {
                var _this = this;
                this.userData.changes.subscribe(function () {
                    return _this.updateUserData();
                });
                this.updateUserData();
                // init tooltips
                // $('[data-toggle="tooltip"]').tooltip();
            };
            NavbarComponent.prototype.updateUserData = function () {
                if (this.userData.loggedIn()) {
                    this.username = this.userData.username;
                    this.dictionaryName = this.userData.dictionaryDisplayName;
                }
                else {
                    this.username = null;
                    this.dictionaryName = null;
                }
            };
            NavbarComponent.prototype.logOut = function () {
                this.userData.logOut();
            };
            NavbarComponent = __decorate([
                Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["n" /* Component */])({
                    selector: 'app-navbar',
                    template: __webpack_require__("./src/app/navbar/navbar.component.html"),
                    styles: [__webpack_require__("./src/app/navbar/navbar.component.css")]
                }),
                __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1__user_user_data_service__["a" /* UserDataService */]])
            ], NavbarComponent);
            return NavbarComponent;
        }());


        /***/
    }),

    /***/ "./src/app/rest/PayloadResponse.ts":
    /***/ (function (module, __webpack_exports__, __webpack_require__) {

        "use strict";
        /* harmony export (binding) */
        __webpack_require__.d(__webpack_exports__, "a", function () {
            return PayloadResponse;
        });
        var PayloadResponse = /** @class */ (function () {
            function PayloadResponse() {
            }

            PayloadResponse.isOk = function (response) {
                return response.httpStatus == 200;
            };
            return PayloadResponse;
        }());


        /***/
    }),

    /***/ "./src/app/routing/app-routing.module.ts":
    /***/ (function (module, __webpack_exports__, __webpack_require__) {

        "use strict";
        /* harmony export (binding) */
        __webpack_require__.d(__webpack_exports__, "a", function () {
            return AppRoutingModule;
        });
        /* harmony import */
        var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("./node_modules/@angular/core/esm5/core.js");
        /* harmony import */
        var __WEBPACK_IMPORTED_MODULE_1__angular_router__ = __webpack_require__("./node_modules/@angular/router/esm5/router.js");
        /* harmony import */
        var __WEBPACK_IMPORTED_MODULE_2__errors_page_not_found_component__ = __webpack_require__("./src/app/errors/page-not-found.component.ts");
        /* harmony import */
        var __WEBPACK_IMPORTED_MODULE_3__top_top_word_component__ = __webpack_require__("./src/app/top/top-word.component.ts");
        /* harmony import */
        var __WEBPACK_IMPORTED_MODULE_4__addword_add_word_component__ = __webpack_require__("./src/app/addword/add-word.component.ts");
        /* harmony import */
        var __WEBPACK_IMPORTED_MODULE_5__settings_settings_component__ = __webpack_require__("./src/app/settings/settings.component.ts");
        /* harmony import */
        var __WEBPACK_IMPORTED_MODULE_6__stats_statistics_component__ = __webpack_require__("./src/app/stats/statistics.component.ts");
        /* harmony import */
        var __WEBPACK_IMPORTED_MODULE_7__wordslist_words_list_component__ = __webpack_require__("./src/app/wordslist/words-list.component.ts");
        /* harmony import */
        var __WEBPACK_IMPORTED_MODULE_8__auth_guard_service__ = __webpack_require__("./src/app/routing/auth-guard.service.ts");
        var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
            var c = arguments.length,
                r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc,
                d;
            if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
            else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
            return c > 3 && r && Object.defineProperty(target, key, r), r;
        };


        var routes = [
            {path: '', redirectTo: '/top', pathMatch: 'full'},
            {
                path: 'top',
                component: __WEBPACK_IMPORTED_MODULE_3__top_top_word_component__["a" /* TopWordComponent */],
                canActivate: [__WEBPACK_IMPORTED_MODULE_8__auth_guard_service__["a" /* AuthGuardService */]],
                runGuardsAndResolvers: 'always'
            },
            {
                path: 'list',
                component: __WEBPACK_IMPORTED_MODULE_7__wordslist_words_list_component__["a" /* WordsListComponent */],
                canActivate: [__WEBPACK_IMPORTED_MODULE_8__auth_guard_service__["a" /* AuthGuardService */]],
                runGuardsAndResolvers: 'always'
            },
            {
                path: 'add',
                component: __WEBPACK_IMPORTED_MODULE_4__addword_add_word_component__["a" /* AddWordComponent */],
                canActivate: [__WEBPACK_IMPORTED_MODULE_8__auth_guard_service__["a" /* AuthGuardService */]],
                runGuardsAndResolvers: 'always'
            },
            {
                path: 'stats',
                component: __WEBPACK_IMPORTED_MODULE_6__stats_statistics_component__["a" /* StatisticsComponent */],
                canActivate: [__WEBPACK_IMPORTED_MODULE_8__auth_guard_service__["a" /* AuthGuardService */]],
                runGuardsAndResolvers: 'always'
            },
            {
                path: 'settings',
                component: __WEBPACK_IMPORTED_MODULE_5__settings_settings_component__["a" /* SettingsComponent */]
            },
            {
                path: '**',
                component: __WEBPACK_IMPORTED_MODULE_2__errors_page_not_found_component__["a" /* PageNotFoundComponent */]
            },
        ];
        var AppRoutingModule = /** @class */ (function () {
            function AppRoutingModule() {
            }

            AppRoutingModule = __decorate([
                Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["I" /* NgModule */])({
                    imports: [__WEBPACK_IMPORTED_MODULE_1__angular_router__["d" /* RouterModule */].forRoot(routes, {
                        useHash: true,
                        onSameUrlNavigation: 'reload'
                    })],
                    exports: [__WEBPACK_IMPORTED_MODULE_1__angular_router__["d" /* RouterModule */]]
                })
            ], AppRoutingModule);
            return AppRoutingModule;
        }());


        /***/
    }),

    /***/ "./src/app/routing/auth-guard.service.ts":
    /***/ (function (module, __webpack_exports__, __webpack_require__) {

        "use strict";
        /* harmony export (binding) */
        __webpack_require__.d(__webpack_exports__, "a", function () {
            return AuthGuardService;
        });
        /* harmony import */
        var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("./node_modules/@angular/core/esm5/core.js");
        /* harmony import */
        var __WEBPACK_IMPORTED_MODULE_1__angular_router__ = __webpack_require__("./node_modules/@angular/router/esm5/router.js");
        /* harmony import */
        var __WEBPACK_IMPORTED_MODULE_2__user_user_data_service__ = __webpack_require__("./src/app/user/user-data.service.ts");
        /* harmony import */
        var __WEBPACK_IMPORTED_MODULE_3__alert_alert_service__ = __webpack_require__("./src/app/alert/alert.service.ts");
        var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
            var c = arguments.length,
                r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc,
                d;
            if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
            else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
            return c > 3 && r && Object.defineProperty(target, key, r), r;
        };
        var __metadata = (this && this.__metadata) || function (k, v) {
            if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
        };


        var AuthGuardService = /** @class */ (function () {
            function AuthGuardService(router, userDataService, alertService) {
                this.router = router;
                this.userDataService = userDataService;
                this.alertService = alertService;
            }

            AuthGuardService.prototype.canActivate = function (route, state) {
                var _this = this;
                if (!this.userDataService.loggedIn()) {
                    // not logged in - redirect to login page with return url
                    this.router.navigate(['/settings'], {queryParams: {returnUrl: state.url}}).then(function () {
                        _this.alertService.warn('Choose an user and dictionary.');
                    });
                    return false;
                }
                return true;
            };
            AuthGuardService = __decorate([
                Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["A" /* Injectable */])(),
                __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1__angular_router__["c" /* Router */], __WEBPACK_IMPORTED_MODULE_2__user_user_data_service__["a" /* UserDataService */], __WEBPACK_IMPORTED_MODULE_3__alert_alert_service__["a" /* AlertService */]])
            ], AuthGuardService);
            return AuthGuardService;
        }());


        /***/
    }),

    /***/ "./src/app/settings/settings.component.css":
    /***/ (function (module, exports) {

        module.exports = ".row-form-center {\n  text-align: center;\n  margin-top: 10px;\n}\n\n.row-form-center > label {\n  text-align: left;\n  margin-top: 10px;\n  width: 100%;\n}\n"

        /***/
    }),

    /***/ "./src/app/settings/settings.component.html":
    /***/ (function (module, exports) {

        module.exports = "<div class=\"panel panel-primary\">\n  <div class=\"panel-heading\" id=\"dict-word\">Settings</div>\n  <div class=\"panel-body\">\n\n    <div class=\"row-form-center\">\n      <label for=\"userLogin\">User:</label>\n      <select class=\"form-control\" [(ngModel)]=\"userId\"\n              id=\"userLogin\" name=\"userLogin\">\n        <option *ngFor=\"let user of users\" [ngValue]=\"user.id\">\n          {{user.login}}\n        </option>\n      </select>\n    </div>\n\n    <div class=\"row-form-center\">\n      <label for=\"dictionaryCode\">Dictionary:</label>\n      <select class=\"form-control\" [(ngModel)]=\"dictionaryCode\"\n              id=\"dictionaryCode\" name=\"dictionaryCode\">\n        <option *ngFor=\"let dict of dicts\" [ngValue]=\"dict.code\">\n          {{dict.displayName}}\n        </option>\n      </select>\n    </div>\n\n    <div class=\"row-form-center\">\n      <button type=\"submit\" class=\"btn btn-success\" (click)=\"saveSettings()\">\n        <span class=\"glyphicon glyphicon-ok\" aria-hidden=\"true\"></span> Save\n      </button>\n    </div>\n\n  </div>\n</div>\n"

        /***/
    }),

    /***/ "./src/app/settings/settings.component.ts":
    /***/ (function (module, __webpack_exports__, __webpack_require__) {

        "use strict";
        /* harmony export (binding) */
        __webpack_require__.d(__webpack_exports__, "a", function () {
            return SettingsComponent;
        });
        /* harmony import */
        var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("./node_modules/@angular/core/esm5/core.js");
        /* harmony import */
        var __WEBPACK_IMPORTED_MODULE_1__user_user_data_service__ = __webpack_require__("./src/app/user/user-data.service.ts");
        /* harmony import */
        var __WEBPACK_IMPORTED_MODULE_2__angular_common_http__ = __webpack_require__("./node_modules/@angular/common/esm5/http.js");
        /* harmony import */
        var __WEBPACK_IMPORTED_MODULE_3__alert_alert_service__ = __webpack_require__("./src/app/alert/alert.service.ts");
        /* harmony import */
        var __WEBPACK_IMPORTED_MODULE_4__angular_router__ = __webpack_require__("./node_modules/@angular/router/esm5/router.js");
        /* harmony import */
        var __WEBPACK_IMPORTED_MODULE_5_rxjs_add_operator_filter__ = __webpack_require__("./node_modules/rxjs/_esm5/add/operator/filter.js");
        /* harmony import */
        var __WEBPACK_IMPORTED_MODULE_6_rxjs_add_operator_map__ = __webpack_require__("./node_modules/rxjs/_esm5/add/operator/map.js");
        var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
            var c = arguments.length,
                r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc,
                d;
            if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
            else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
            return c > 3 && r && Object.defineProperty(target, key, r), r;
        };
        var __metadata = (this && this.__metadata) || function (k, v) {
            if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
        };


        var SettingsComponent = /** @class */ (function () {
            function SettingsComponent(http, userData, alertService, route, router) {
                this.http = http;
                this.userData = userData;
                this.alertService = alertService;
                this.route = route;
                this.router = router;
                this.users = [];
                this.dicts = [];
                if (this.userData.loggedIn()) {
                    this.userId = this.userData.userId;
                    this.dictionaryCode = this.userData.dictionaryCode;
                }
            }

            SettingsComponent.prototype.ngOnInit = function () {
                var _this = this;
                // populate user list
                var url = "/api/user";
                this.http.get(url).subscribe(function (response) {
                    _this.users = response;
                    if (!_this.userId && _this.users.length > 0) {
                        _this.userId = _this.users[0].id;
                    }
                }, function (err) {
                    return console.log(err);
                });
                // populate dicts list
                url = "/api/dictionary";
                this.http.get(url).subscribe(function (response) {
                    return _this.buildDicts(response);
                }, function (err) {
                    return console.log(err);
                });
            };
            SettingsComponent.prototype.ngAfterContentChecked = function () {
                // get return url from route parameters or default
                this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || null;
            };
            SettingsComponent.prototype.buildDicts = function (dictsRaw) {
                this.dicts = [];
                for (var _i = 0, dictsRaw_1 = dictsRaw; _i < dictsRaw_1.length; _i++) {
                    var dictRaw = dictsRaw_1[_i];
                    this.dicts.push({
                        code: dictRaw.sourceLanguage + '-' + dictRaw.targetLanguage,
                        displayName: dictRaw.sourceLanguage + ' -> ' + dictRaw.targetLanguage
                    });
                    this.dicts.push({
                        code: dictRaw.sourceLanguage + '-' + dictRaw.targetLanguage + '-r',
                        displayName: dictRaw.sourceLanguage + ' <- ' + dictRaw.targetLanguage
                    });
                }
                if (!this.dictionaryCode && this.dicts.length > 0) {
                    this.dictionaryCode = this.dicts[0].code;
                }
            };
            SettingsComponent.prototype.saveSettings = function () {
                var _this = this;
                var username = this.users
                    .filter(function (user) {
                        return user.id == _this.userId;
                    })
                    .map(function (user) {
                        return user.login;
                    })
                    .pop();
                if (username == null) {
                    this.alertService.error('No user has been selected.');
                    return;
                }
                if (this.dictionaryCode == undefined || this.dictionaryCode == 'undefined') {
                    this.alertService.error('No dictionary has been selected.');
                    return;
                }
                this.userData.setUser(this.userId, username);
                this.userData.setDictionary(this.dictionaryCode);
                this.alertService.success('Settings have been successfully saved.');
                // redirect to return url
                if (this.returnUrl) {
                    this.router.navigateByUrl(this.returnUrl);
                }
            };
            SettingsComponent = __decorate([
                Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["n" /* Component */])({
                    selector: 'app-settings',
                    template: __webpack_require__("./src/app/settings/settings.component.html"),
                    styles: [__webpack_require__("./src/app/settings/settings.component.css")]
                }),
                __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_2__angular_common_http__["a" /* HttpClient */], __WEBPACK_IMPORTED_MODULE_1__user_user_data_service__["a" /* UserDataService */], __WEBPACK_IMPORTED_MODULE_3__alert_alert_service__["a" /* AlertService */], __WEBPACK_IMPORTED_MODULE_4__angular_router__["a" /* ActivatedRoute */],
                    __WEBPACK_IMPORTED_MODULE_4__angular_router__["c" /* Router */]])
            ], SettingsComponent);
            return SettingsComponent;
        }());


        /***/
    }),

    /***/ "./src/app/stats/statistics.component.css":
    /***/ (function (module, exports) {

        module.exports = ".statistics-dictionary-name {\n  font-weight: bold;\n}\n\n/* progress bars with centered text */\n\n.progress {\n  position: relative;\n}\n\n.progress span.progress-centered {\n  position: absolute;\n  display: block;\n  width: 100%;\n  color: #000;\n  font-weight: bold;\n}\n"

        /***/
    }),

    /***/ "./src/app/stats/statistics.component.html":
    /***/ (function (module, exports) {

        module.exports = "<div class=\"panel panel-primary\">\n  <div class=\"panel-heading\" id=\"dict-word\">Statistics</div>\n  <div class=\"panel-body\">\n\n    <div *ngFor=\"let dictStat of dictStats\">\n      <div class=\"panel panel-default\">\n        <div class=\"panel-heading statistics-dictionary-name\">\n          Dictionary: {{dictStat.dictDisplayName}}\n        </div>\n        <div class=\"panel-body\">\n\n          <h5>Trained words:</h5>\n          <div class=\"progress\">\n            <div class=\"progress-bar progress-bar-success progress-bar-striped\"\n                 role=\"progressbar\"\n                 [attr.aria-valuenow]=\"dictStat.trained?.percentage\"\n                 aria-valuemin=\"0\" aria-valuemax=\"100\"\n                 [style.width]=\"dictStat.trained?.percentage + '%'\">\n          <span class=\"progress-centered\">\n          <span>{{dictStat.trained?.count}}</span>\n          /\n          <span>{{dictStat.allCount}}</span>\n          </span>\n            </div>\n          </div>\n\n          <h5>Training in progress:</h5>\n          <div class=\"progress\">\n            <div class=\"progress-bar progress-bar-warning progress-bar-striped\"\n                 role=\"progressbar\"\n                 [attr.aria-valuenow]=\"dictStat.trainingInProgress?.percentage\"\n                 aria-valuemin=\"0\" aria-valuemax=\"100\"\n                 [style.width]=\"dictStat.trainingInProgress?.percentage + '%'\">\n          <span class=\"progress-centered\">\n          <span>{{dictStat.trainingInProgress?.count}}</span>\n          /\n          <span>{{dictStat.allCount}}</span>\n          </span>\n            </div>\n          </div>\n\n          <h5>Touched words:</h5>\n          <div class=\"progress\">\n            <div class=\"progress-bar progress-bar-striped\"\n                 role=\"progressbar\"\n                 [attr.aria-valuenow]=\"dictStat.touched?.percentage\"\n                 aria-valuemin=\"0\" aria-valuemax=\"100\"\n                 [style.width]=\"dictStat.touched?.percentage + '%'\">\n          <span class=\"progress-centered\">\n          <span>{{dictStat.touched?.count}}</span>\n          /\n          <span>{{dictStat.allCount}}</span>\n          </span>\n            </div>\n          </div>\n\n          <h5>Cooling down:</h5>\n          <div class=\"progress\">\n            <div class=\"progress-bar progress-bar-danger progress-bar-striped\"\n                 role=\"progressbar\"\n                 [attr.aria-valuenow]=\"dictStat.coolingDown?.percentage\"\n                 aria-valuemin=\"0\" aria-valuemax=\"100\"\n                 [style.width]=\"dictStat.coolingDown?.percentage + '%'\">\n          <span class=\"progress-centered\">\n          <span>{{dictStat.coolingDown?.count}}</span>\n          /\n          <span>{{dictStat.allCount}}</span>\n          </span>\n            </div>\n          </div>\n\n        </div>\n      </div>\n    </div>\n\n  </div>\n</div>\n"

        /***/
    }),

    /***/ "./src/app/stats/statistics.component.ts":
    /***/ (function (module, __webpack_exports__, __webpack_require__) {

        "use strict";
        /* harmony export (binding) */
        __webpack_require__.d(__webpack_exports__, "a", function () {
            return StatisticsComponent;
        });
        /* harmony import */
        var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("./node_modules/@angular/core/esm5/core.js");
        /* harmony import */
        var __WEBPACK_IMPORTED_MODULE_1__angular_common_http__ = __webpack_require__("./node_modules/@angular/common/esm5/http.js");
        /* harmony import */
        var __WEBPACK_IMPORTED_MODULE_2__user_user_data_service__ = __webpack_require__("./src/app/user/user-data.service.ts");
        var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
            var c = arguments.length,
                r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc,
                d;
            if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
            else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
            return c > 3 && r && Object.defineProperty(target, key, r), r;
        };
        var __metadata = (this && this.__metadata) || function (k, v) {
            if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
        };


        var StatisticsComponent = /** @class */ (function () {
            function StatisticsComponent(http, userData) {
                this.http = http;
                this.userData = userData;
                this.dictStats = [];
            }

            StatisticsComponent.prototype.ngOnInit = function () {
                var _this = this;
                var userId = this.userData.userId;
                var dictionaryCode = this.userData.dictionaryCode;
                var url = "/api/stats/" + userId + "/" + dictionaryCode;
                return this.http.get(url).subscribe(function (response) {
                    return _this.dictStats = response;
                }, function (err) {
                    return console.log(err);
                });
            };
            StatisticsComponent = __decorate([
                Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["n" /* Component */])({
                    selector: 'app-statistics',
                    template: __webpack_require__("./src/app/stats/statistics.component.html"),
                    styles: [__webpack_require__("./src/app/stats/statistics.component.css")]
                }),
                __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1__angular_common_http__["a" /* HttpClient */], __WEBPACK_IMPORTED_MODULE_2__user_user_data_service__["a" /* UserDataService */]])
            ], StatisticsComponent);
            return StatisticsComponent;
        }());


        /***/
    }),

    /***/ "./src/app/top/top-word.component.css":
    /***/ (function (module, exports) {

        module.exports = "#button-answer-correct, #button-answer-wrong {\n  display: none;\n}\n\n#dict-word {\n  text-align: center;\n  font-weight: bold;\n  font-size: 1.2em;\n}\n\n#dict-definition {\n  min-height: 3em;\n  text-align: center;\n}\n\n#buttons-panel {\n  text-align: center;\n}\n"

        /***/
    }),

    /***/ "./src/app/top/top-word.component.html":
    /***/ (function (module, exports) {

        module.exports = "<div class=\"panel panel-primary\">\n  <div class=\"panel-heading\">\n    <div id=\"dict-word\">{{displayWordName}}</div>\n  </div>\n  <div class=\"panel-body\">\n    <div id=\"dict-definition\">{{displayDefinition}}</div>\n  </div>\n  <div class=\"panel-footer\" id=\"buttons-panel\">\n    <button type=\"button\" id=\"button-check\" class=\"btn btn-default\" (click)=\"checkAnswer()\">\n      <span class=\"glyphicon glyphicon-question-sign\" aria-hidden=\"true\"></span> Check\n    </button>\n    <button type=\"button\" id=\"button-answer-correct\" class=\"btn btn-success\"\n            (click)=\"answerCorrect()\">\n      <span class=\"glyphicon glyphicon-ok\" aria-hidden=\"true\"></span> Correct\n    </button>\n    <button type=\"button\" id=\"button-answer-wrong\" class=\"btn btn-danger\" (click)=\"answerWrong()\">\n      <span class=\"glyphicon glyphicon-remove\" aria-hidden=\"true\"></span> Wrong\n    </button>\n    <button type=\"button\" id=\"button-skip\" class=\"btn btn-primary\" (click)=\"skipWord()\">\n      <span class=\"glyphicon glyphicon-step-forward\" aria-hidden=\"true\"></span> Skip\n    </button>\n  </div>\n</div>\n\n<div class=\"well\">\n  <div class=\"dictentry-row\">\n    Rank: {{topWord?.rankValue}}\n  </div>\n  <div class=\"dictentry-row\">\n    Tries count: {{topWord?.triesCount}}\n  </div>\n  <div class=\"dictentry-row\">\n    Last use: {{topWord?.lastUse}}\n  </div>\n</div>\n"

        /***/
    }),

    /***/ "./src/app/top/top-word.component.ts":
    /***/ (function (module, __webpack_exports__, __webpack_require__) {

        "use strict";
        /* harmony export (binding) */
        __webpack_require__.d(__webpack_exports__, "a", function () {
            return TopWordComponent;
        });
        /* harmony import */
        var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("./node_modules/@angular/core/esm5/core.js");
        /* harmony import */
        var __WEBPACK_IMPORTED_MODULE_1__angular_common_http__ = __webpack_require__("./node_modules/@angular/common/esm5/http.js");
        /* harmony import */
        var __WEBPACK_IMPORTED_MODULE_2__user_user_data_service__ = __webpack_require__("./src/app/user/user-data.service.ts");
        var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
            var c = arguments.length,
                r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc,
                d;
            if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
            else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
            return c > 3 && r && Object.defineProperty(target, key, r), r;
        };
        var __metadata = (this && this.__metadata) || function (k, v) {
            if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
        };


        var fadeTime = 500;
        var TopWordComponent = /** @class */ (function () {
            function TopWordComponent(http, userData) {
                this.http = http;
                this.userData = userData;
                this.reversedDictionary = false;
            }

            TopWordComponent.prototype.ngOnInit = function () {
                this.nextTopWordInit();
            };
            TopWordComponent.prototype.nextTopWordInit = function () {
                var _this = this;
                var userId = this.userData.userId;
                var dictionaryCode = this.userData.dictionaryCode;
                var url = "/api/rank/top/" + userId + "/" + dictionaryCode;
                return this.http.get(url).subscribe(function (response) {
                    return _this.onTopWordReceived(response);
                }, function (err) {
                    return console.log(err);
                });
            };
            TopWordComponent.prototype.onTopWordReceived = function (topWord) {
                this.topWord = topWord;
                if (this.topWord == null) {
                    $("#button-skip").hide();
                    $("#button-check").hide();
                    $("#button-answer-correct").hide();
                    $("#button-answer-wrong").hide();
                }
                else {
                    if (this.reversedDictionary) {
                        this.displayWordName = '';
                        this.displayDefinition = this.topWord.definition;
                    }
                    else {
                        this.displayWordName = this.topWord.wordName;
                        this.displayDefinition = '';
                    }
                    $("#button-check").show();
                    $("#button-answer-correct").hide();
                    $("#button-answer-wrong").hide();
                }
            };
            TopWordComponent.prototype.checkAnswer = function () {
                $("#button-check").hide();
                $("#button-answer-correct").fadeIn(fadeTime);
                $("#button-answer-wrong").fadeIn(fadeTime);
                this.displayWordName = this.topWord.wordName;
                this.displayDefinition = this.topWord.definition;
                if (!this.reversedDictionary) {
                    $("#dict-definition")
                        .hide()
                        .fadeIn(fadeTime);
                }
                else {
                    $("#dict-word")
                        .hide()
                        .fadeIn(fadeTime);
                }
            };
            TopWordComponent.prototype.answerCorrect = function () {
                this.clickedWordAction('answer/correct');
            };
            TopWordComponent.prototype.answerWrong = function () {
                this.clickedWordAction('answer/wrong');
            };
            TopWordComponent.prototype.skipWord = function () {
                this.clickedWordAction('skip');
            };
            TopWordComponent.prototype.clickedWordAction = function (endpointAction) {
                var _this = this;
                var rankId = this.topWord.rankId;
                var url = "/api/rank/" + rankId + "/" + endpointAction;
                this.http.post(url, null).subscribe(function () {
                    _this.nextTopWordInit();
                }, function (err) {
                    return console.log(err);
                });
            };
            TopWordComponent = __decorate([
                Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["n" /* Component */])({
                    selector: 'app-top-word',
                    template: __webpack_require__("./src/app/top/top-word.component.html"),
                    styles: [__webpack_require__("./src/app/top/top-word.component.css")]
                }),
                __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1__angular_common_http__["a" /* HttpClient */], __WEBPACK_IMPORTED_MODULE_2__user_user_data_service__["a" /* UserDataService */]])
            ], TopWordComponent);
            return TopWordComponent;
        }());


        /***/
    }),

    /***/ "./src/app/user/user-data.service.ts":
    /***/ (function (module, __webpack_exports__, __webpack_require__) {

        "use strict";
        /* harmony export (binding) */
        __webpack_require__.d(__webpack_exports__, "a", function () {
            return UserDataService;
        });
        /* harmony import */
        var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("./node_modules/@angular/core/esm5/core.js");
        /* harmony import */
        var __WEBPACK_IMPORTED_MODULE_1__angular_router__ = __webpack_require__("./node_modules/@angular/router/esm5/router.js");
        var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
            var c = arguments.length,
                r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc,
                d;
            if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
            else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
            return c > 3 && r && Object.defineProperty(target, key, r), r;
        };
        var __metadata = (this && this.__metadata) || function (k, v) {
            if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
        };


        var UserDataService = /** @class */ (function () {
            function UserDataService(router) {
                this.router = router;
                this.changes = new __WEBPACK_IMPORTED_MODULE_0__angular_core__["v" /* EventEmitter */]();
                this.storage = sessionStorage; // sessionStorage or localStorage
                if (this.storage.getItem('userId')) {
                    this.userId = parseInt(this.storage.getItem('userId'));
                    this.username = this.storage.getItem('username');
                }
                this.setDictionary(this.storage.getItem('dictionaryCode'));
            }

            UserDataService.prototype.ngOnInit = function () {
            };
            Object.defineProperty(UserDataService.prototype, "dictionaryCode", {
                get: function () {
                    return this.dictionary + (this.reversedDictionary ? '-r' : '');
                },
                enumerable: true,
                configurable: true
            });
            Object.defineProperty(UserDataService.prototype, "dictionaryDisplayName", {
                get: function () {
                    if (this.dictionary) {
                        if (this.reversedDictionary) {
                            return this.dictionary.replace('-', '<-');
                        }
                        else {
                            return this.dictionary.replace('-', '->');
                        }
                    }
                    else {
                        return null;
                    }
                },
                enumerable: true,
                configurable: true
            });
            UserDataService.prototype.setUser = function (userId, username) {
                this.userId = userId;
                this.username = username;
                this.storage.setItem('userId', '' + this.userId);
                this.storage.setItem('username', this.username);
                this.changes.emit(); // emit settings changed
            };
            UserDataService.prototype.setDictionary = function (dictionaryCode) {
                if (dictionaryCode) {
                    this.dictionary = dictionaryCode.substr(0, 5);
                    this.reversedDictionary = dictionaryCode.endsWith('-r');
                    this.storage.setItem('dictionaryCode', dictionaryCode);
                }
                this.changes.emit(); // emit settings changed
            };
            UserDataService.prototype.loggedIn = function () {
                return this.userId != null && this.dictionary != null;
            };
            UserDataService.prototype.logOut = function () {
                this.userId = null;
                this.username = null;
                this.dictionary = null;
                this.reversedDictionary = null;
                this.storage.clear();
                this.changes.emit(); // emit settings changed
                this.router.navigate([this.router.url]);
            };
            UserDataService = __decorate([
                Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["A" /* Injectable */])(),
                __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1__angular_router__["c" /* Router */]])
            ], UserDataService);
            return UserDataService;
        }());


        /***/
    }),

    /***/ "./src/app/wordrank/word-rank.service.ts":
    /***/ (function (module, __webpack_exports__, __webpack_require__) {

        "use strict";
        /* harmony export (binding) */
        __webpack_require__.d(__webpack_exports__, "a", function () {
            return WordRankService;
        });
        /* harmony import */
        var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("./node_modules/@angular/core/esm5/core.js");
        /* harmony import */
        var __WEBPACK_IMPORTED_MODULE_1__angular_common_http__ = __webpack_require__("./node_modules/@angular/common/esm5/http.js");
        /* harmony import */
        var __WEBPACK_IMPORTED_MODULE_2_rxjs_add_observable_of__ = __webpack_require__("./node_modules/rxjs/_esm5/add/observable/of.js");
        /* harmony import */
        var __WEBPACK_IMPORTED_MODULE_3__user_user_data_service__ = __webpack_require__("./src/app/user/user-data.service.ts");
        var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
            var c = arguments.length,
                r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc,
                d;
            if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
            else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
            return c > 3 && r && Object.defineProperty(target, key, r), r;
        };
        var __metadata = (this && this.__metadata) || function (k, v) {
            if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
        };


        var WordRankService = /** @class */ (function () {
            function WordRankService(http, userData) {
                this.http = http;
                this.userData = userData;
            }

            WordRankService.prototype.getAllWordRanks = function () {
                var userId = this.userData.userId;
                var dictionaryCode = this.userData.dictionaryCode;
                var url = "/api/rank/all/" + userId + "/" + dictionaryCode;
                return this.http.get(url);
            };
            WordRankService = __decorate([
                Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["A" /* Injectable */])(),
                __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1__angular_common_http__["a" /* HttpClient */], __WEBPACK_IMPORTED_MODULE_3__user_user_data_service__["a" /* UserDataService */]])
            ], WordRankService);
            return WordRankService;
        }());


        /***/
    }),

    /***/ "./src/app/wordslist/words-list.component.css":
    /***/ (function (module, exports) {

        module.exports = ""

        /***/
    }),

    /***/ "./src/app/wordslist/words-list.component.html":
    /***/ (function (module, exports) {

        module.exports = "<div class=\"panel panel-primary\">\n  <div class=\"panel-heading\" id=\"dict-word\">Dictionary words</div>\n  <div class=\"panel-body\">\n\n    <table class=\"table table-striped table-hover\">\n      <thead class=\"thead-inverse\">\n      <tr>\n        <th>Word</th>\n        <th>Definition</th>\n        <th>Rank</th>\n        <th>Tries</th>\n      </tr>\n      </thead>\n      <tbody>\n      <tr *ngFor=\"let wordrank of wordranks\">\n        <td>{{wordrank?.wordName}}</td>\n        <td>{{wordrank?.definition}}</td>\n        <td>{{wordrank?.rankValue}}</td>\n        <td>{{wordrank?.triesCount}}</td>\n      </tr>\n      </tbody>\n    </table>\n\n  </div>\n</div>\n"

        /***/
    }),

    /***/ "./src/app/wordslist/words-list.component.ts":
    /***/ (function (module, __webpack_exports__, __webpack_require__) {

        "use strict";
        /* harmony export (binding) */
        __webpack_require__.d(__webpack_exports__, "a", function () {
            return WordsListComponent;
        });
        /* harmony import */
        var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("./node_modules/@angular/core/esm5/core.js");
        /* harmony import */
        var __WEBPACK_IMPORTED_MODULE_1__wordrank_word_rank_service__ = __webpack_require__("./src/app/wordrank/word-rank.service.ts");
        var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
            var c = arguments.length,
                r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc,
                d;
            if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
            else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
            return c > 3 && r && Object.defineProperty(target, key, r), r;
        };
        var __metadata = (this && this.__metadata) || function (k, v) {
            if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
        };


        var WordsListComponent = /** @class */ (function () {
            function WordsListComponent(wordRankService) {
                this.wordRankService = wordRankService;
                this.wordranks = [];
            }

            WordsListComponent.prototype.ngOnInit = function () {
                var _this = this;
                this.wordRankService.getAllWordRanks().subscribe(function (wordranks) {
                    return _this.wordranks = wordranks;
                });
            };
            WordsListComponent = __decorate([
                Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["n" /* Component */])({
                    selector: 'app-words-list',
                    template: __webpack_require__("./src/app/wordslist/words-list.component.html"),
                    styles: [__webpack_require__("./src/app/wordslist/words-list.component.css")]
                }),
                __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1__wordrank_word_rank_service__["a" /* WordRankService */]])
            ], WordsListComponent);
            return WordsListComponent;
        }());


        /***/
    }),

    /***/ "./src/environments/environment.ts":
    /***/ (function (module, __webpack_exports__, __webpack_require__) {

        "use strict";
        /* harmony export (binding) */
        __webpack_require__.d(__webpack_exports__, "a", function () {
            return environment;
        });
// The file contents for the current environment will overwrite these during build.
// The build system defaults to the dev environment which uses `environment.ts`, but if you do
// `ng build --env=prod` then `environment.prod.ts` will be used instead.
// The list of which env maps to which file can be found in `.angular-cli.json`.
        var environment = {
            production: false
        };


        /***/
    }),

    /***/ "./src/main.ts":
    /***/ (function (module, __webpack_exports__, __webpack_require__) {

        "use strict";
        Object.defineProperty(__webpack_exports__, "__esModule", {value: true});
        /* harmony import */
        var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("./node_modules/@angular/core/esm5/core.js");
        /* harmony import */
        var __WEBPACK_IMPORTED_MODULE_1__angular_platform_browser_dynamic__ = __webpack_require__("./node_modules/@angular/platform-browser-dynamic/esm5/platform-browser-dynamic.js");
        /* harmony import */
        var __WEBPACK_IMPORTED_MODULE_2__app_app_module__ = __webpack_require__("./src/app/app.module.ts");
        /* harmony import */
        var __WEBPACK_IMPORTED_MODULE_3__environments_environment__ = __webpack_require__("./src/environments/environment.ts");


        if (__WEBPACK_IMPORTED_MODULE_3__environments_environment__["a" /* environment */].production) {
            Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["_13" /* enableProdMode */])();
        }
        Object(__WEBPACK_IMPORTED_MODULE_1__angular_platform_browser_dynamic__["a" /* platformBrowserDynamic */])().bootstrapModule(__WEBPACK_IMPORTED_MODULE_2__app_app_module__["a" /* AppModule */])
            .catch(function (err) {
                return console.log(err);
            });


        /***/
    }),

    /***/ 0:
    /***/ (function (module, exports, __webpack_require__) {

        module.exports = __webpack_require__("./src/main.ts");


        /***/
    })

}, [0]);
//# sourceMappingURL=main.bundle.js.map