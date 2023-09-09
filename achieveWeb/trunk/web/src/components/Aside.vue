<template>
  <n-h2 style="margin-top: 20px;--n-margin: 28px 0 10px 0; color:white">Achievement WEB</n-h2>
  <n-menu
    style="--n-item-color-active: #636b75;--n-item-color-active-hover: #636b75;--n-item-color-active-collapsed: #636b75;"
    :inverted="true" :collapsed-width="64" :collapsed-icon-size="22" :options="menuOptions" />
</template>
 
<script setup>
import { h } from "vue";
import { NIcon } from "naive-ui";
import { RouterLink } from 'vue-router'
import {
  BookOutline as BookIcon,
  PersonOutline as PersonIcon,
  HomeOutline as HomeOutline,
  BookmarksOutline as BookmarksOutline,
  BookmarkOutline as BookmarkOutline,
  TrashBinOutline as TrashBinOutline
} from "@vicons/ionicons5";

function renderIcon(icon) {
  return () => h(NIcon, null, { default: () => h(icon) });
}

const menuOptions = [
  {
    label: () =>
      h(
        RouterLink,
        {
          to: {
            name: 'index',
          }
        },
        '主页'
      ),
    key: "index",
    icon: renderIcon(HomeOutline)
  },
  {
    label: () =>
      h(
        RouterLink,
        {
          to: {
            name: 'userInfo',
          }
        },
        '个人资料'
      ),
    key: "personInfo",
    icon: renderIcon(PersonIcon)
  },
  {
    label: "项目管理",
    key: "manageItem",
    icon: renderIcon(BookmarksOutline),
    children: [
      {
        label: () =>
          h(
            RouterLink,
            {
              to: {
                name: 'category',
              }
            },
            '分类管理'
          ),
        icon: renderIcon(BookmarkOutline),
        key: "category"
      },
      {
        label: () =>
          h(
            RouterLink,
            {
              to: {
                name: 'tag',
              }
            },
            '标签管理'
          ),
        icon: renderIcon(BookmarkOutline),
        key: "tag"
      },
      {
        label: () =>
          h(
            RouterLink,
            {
              to: {
                name: 'itemRecord',
              }
            },
            '记录管理'
          ),
        icon: renderIcon(BookmarkOutline),
        key: "itemRecord"
      },
      // {
      //   label: "创建项目记录",
      //   key: "itemRecord"
      // },
      // {
      //   label: "添加标签",
      //   key: "addTag"
      // }
    ]
  },
  {
    label: "项目列表",
    key: "itemList",
    icon: renderIcon(BookIcon),
    children: [
      {
        label: "项目菜单",
        key: "itemMenu"
      },
      {
        label: "项目总览",
        key: "itemOverview"
      },
    ]
  },
];

</script>
 
<style scoped lang="scss">
::v-deep .n-menu .n-menu-item-content .n-menu-item-content-header {
  margin-left: 16px;
  text-align: left;
  color: white;
}
</style>