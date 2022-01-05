<template>
  <span>{{ priceFormatter(amount) }}</span>
</template>

<script>
const formatter = new Intl.NumberFormat("en-US", {
  style: "currency",
  currency: "USD",
  minimumFractionDigits: 2,
});
export default {
  name: "Price",
  props: {
    amount: {
      type: Number,
      required: true,
    },
  },
  methods: {
    priceFormatter: function (price) {
      return formatter.format(price / 100.0);
    },
  },
};
</script>

<style scoped></style>
