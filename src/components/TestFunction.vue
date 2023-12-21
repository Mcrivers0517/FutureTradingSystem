<template>
  <div>
    <div class="demo-basic--circle">
      <div class="block">
        <el-avatar shape="square" :size="50" :src="squareUrl"></el-avatar>
      </div>
      <div class="block" v-for="size in sizeList" :key="size">
        <el-avatar shape="square" :size="size" :src="squareUrl"></el-avatar>
      </div>
    </div>
  </div>
</template>