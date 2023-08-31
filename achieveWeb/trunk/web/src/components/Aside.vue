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
        label: "添加项目",
        key: "createProject",
        icon: renderIcon(BookmarkOutline),
        children: [
          {
            label: "创建分类",
            key: "category"
          },
          {
            label: "创建标签",
            key: "tag"
          },
          {
            label: "创建项目",
            key: "item"
          },
          {
            label: "创建项目记录",
            key: "itemRecord"
          },
          {
            label: "添加标签",
            key: "addTag"
          }
        ]
      },
      {
        label: "删除项目",
        key: "deleteProject",
        icon: renderIcon(TrashBinOutline)
      }
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