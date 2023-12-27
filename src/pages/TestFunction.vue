<template>
  <el-table
    :data="PositionData"
    class="futures-table"
    :cell-style="tableRowClassName"
    :default-sort="{ prop: 'CurrentPrice', order: 'descending' }"
  >
    <el-table-column prop="type" label="品种" :width="80" sortable>
    </el-table-column>
    <el-table-column prop="BuyOrSell" label="买/卖"> </el-table-column>
    <el-table-column prop="Position" label="持仓"> </el-table-column>
    <el-table-column prop="FloatingProfitOrLoss" label="浮动盈亏">
    </el-table-column>
    <el-table-column prop="ProfitOrLossRatio" label="盈亏比率">
    </el-table-column>
    <el-table-column prop="AverageOpeningPrice" label="开仓均价">
    </el-table-column>
    <el-table-column prop="CurrentPrice" label="现价"> </el-table-column>
    <el-table-column
      prop="LatestTransactionTime"
      label="最新成交时间"
      :width="175"
    >
    </el-table-column>
  </el-table>
</template>
<script>
export default {
  data() {
    return {
      PositionData: [
        {
          type: "商品A",
          BuyOrSell: "买入",
          Position: 100,
          FloatingProfitOrLoss: 50.25,
          ProfitOrLossRatio: "5%",
          AverageOpeningPrice: 98.75,
          CurrentPrice: 103.0,
          LatestTransactionTime: "2023-01-05 09:45:00",
        },
        {
          type: "商品B",
          BuyOrSell: "卖出",
          Position: 50,
          FloatingProfitOrLoss: -25.8,
          ProfitOrLossRatio: "-2%",
          AverageOpeningPrice: 105.5,
          CurrentPrice: 103.25,
          LatestTransactionTime: "2023-01-05 14:20:00",
        },
        {
          type: "商品C",
          BuyOrSell: "买入",
          Position: 75,
          FloatingProfitOrLoss: 30.1,
          ProfitOrLossRatio: "3.5%",
          AverageOpeningPrice: 55.0,
          CurrentPrice: 57.1,
          LatestTransactionTime: "2023-01-06 10:15:00",
        },
      ],
    };
  },
  methods: {
    formatTooltip(val) {
      return val / 100;
    },
  },
};
</script>