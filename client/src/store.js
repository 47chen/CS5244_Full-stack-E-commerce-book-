import Vue from "vue";
import Vuex from "vuex";
import ApiService from "@/services/ApiService";
import { ShoppingCart } from "@/models/ShoppingCart";

Vue.use(Vuex);
export const CART_STORAGE_KEY = "cart";
export const ORDER_DETAIL_STORAGE_KEY = "orderDetails"

export default new Vuex.Store({
  state: {
    categories: [],
    selectedCategoryName: "", // btn selected categoryNav
    selectedCategoryBooks: [],
    cart: new ShoppingCart(),
    orderDetails: null,
  },

  mutations: {
    //change the state
    SET_CATEGORIES(state, newCategories) {
      state.categories = newCategories;
    },

    SET_SELECTED_CATEGORY_BOOKS(state, newSelectedCategoryBooks) {
      state.selectedCategoryBooks = newSelectedCategoryBooks;
    },

    SELECT_CATEGORY_NAME(state, newSelectedCategoryName) {
      state.selectedCategoryName = newSelectedCategoryName;
    },

    SET_CART(state, shoppingCart) {
      localStorage.setItem(CART_STORAGE_KEY, JSON.stringify(shoppingCart));
      let newCart = new ShoppingCart();
      shoppingCart.items.forEach((item) => {
        newCart.addItem(item.book, item.quantity);
      });
      state.cart = newCart;
    },

    ADD_TO_CART(state, book) {
      state.cart.addItem(book, 1);
      localStorage.setItem(CART_STORAGE_KEY, JSON.stringify(this.state.cart));
    },

    UPDATE_CART(state, { book, quantity }) {
      state.cart.update(book, quantity);
      localStorage.setItem(CART_STORAGE_KEY, JSON.stringify(this.state.cart));
    },

    CLEAR_CART(state) {
      state.cart.clear();
      localStorage.setItem(CART_STORAGE_KEY, JSON.stringify(this.state.cart));
    },

    SET_ORDER_DETAIL(state, orderDetails) {
      state.orderDetails = orderDetails;
      sessionStorage.setItem(ORDER_DETAIL_STORAGE_KEY, JSON.stringify(orderDetails))
    },

    CLEAR_ORDER_DETAIL(state) {
      sessionStorage.removeItem(ORDER_DETAIL_STORAGE_KEY)
      state.orderDetails = null
    }
  },

  actions: {
    fetchCategories(context) {
      ApiService.fetchCategories()
        .then((categories) => {
          console.log("Data: ", categories);
          context.commit("SET_CATEGORIES", categories);
        })
        .catch((reason) => {
          console.log("Error: " + reason);
        });
    },

    fetchSelectedCategoryBooks(context) {
      return ApiService.fetchSelectedCategoryBooks(context.state.selectedCategoryName)
        .then((selectedCategoryBooks) => {
          console.log("Data: " + selectedCategoryBooks);
          context.commit("SET_SELECTED_CATEGORY_BOOKS", selectedCategoryBooks);
        })
        .catch((reason) => {
          console.log("Error: " + reason);
          throw reason;
        });
    },

    selectCategory(context, category) {
      context.commit("SELECT_CATEGORY_NAME", category);
    },

    addToCart(context, book) {
      context.commit("ADD_TO_CART", book);
    },

    updateCart(context, { book, quantity }) {
      context.commit("UPDATE_CART", { book, quantity });
    },

    clearCart(context) {
      context.commit("CLEAR_CART");
    },

    placeOrder({ commit, state }, customerForm) {
      return ApiService.placeOrder({
        cart: state.cart,
        customerForm: customerForm,
      }).then((orderDetails) => {
        commit("CLEAR_CART");
        commit("SET_ORDER_DETAIL", orderDetails)
      });
    },
  },
});
