<!--
 * @Author: GZH
 * @Date: 2021-07-14 20:11:02
 * @LastEditors: GZH
 * @LastEditTime: 2021-07-15 20:03:00
 * @FilePath: \web\src\views\admin\admin-ebook.vue
 * @Description: 
-->
<template>
  <a-layout>
    <a-layout-content :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }">
      <a-table
        :columns="columns"
        :row-key="record => record.id"
        :data-source="ebooks"
        :pagination="pagination"
        :loading="loading"
        @change="handleTableChange"
      >
        <template #cover="{ text: cover }">
          <img v-if="cover" :src="cover" alt="avatar" />
        </template>

        <template v-slot:action="{ record }">
          <a-space size="small">
            <a-button type="primary" @click="edit(record)">
              编辑
            </a-button>

            <a-button type="danger">
              删除
            </a-button>
          </a-space>
        </template>
      </a-table>
    </a-layout-content>
  </a-layout>
  <a-modal title="电子书表单" v-model:visible="modalVisible" :comfirm-loading="modalLoading" @ok="handleModalOk">
    <p>test</p>
  </a-modal>
</template>

<script lang="ts">
import axios from 'axios';
import { defineComponent, onMounted, ref } from 'vue';
import { message } from 'ant-design-vue';

export default defineComponent({
  name: 'adminEbook',
  setup() {
    const loading = ref(false);
    const pagination = ref({
      current: 1,
      pageSize: 4,
      total: 0,
    });
    const ebooks = ref();

    const columns = [
      {
        title: '封面',
        dataIndex: 'cover',
        slots: { customRender: 'cover' },
      },
      {
        title: '名称',
        dataIndex: 'name',
      },
      {
        title: '分类',
      },
      {
        title: '文档数',
        dataIndex: 'docCount',
      },
      {
        title: '阅读数',
        dataIndex: 'viewCount',
      },
      {
        title: '点赞数',
        dataIndex: 'voteCount',
      },
      {
        title: 'Action',
        key: 'action',
        slots: { customRender: 'action' },
      },
    ];

    /**
     * 数据查询
     **/
    onMounted(() => {
      handleQuery({ page: 1, size: pagination.value.pageSize });
    });
    const handleQuery = (params: any) => {
      loading.value = true;
      // 如果不清空现有数据，则编辑保存重新加载数据后，再点编辑，则列表显示的还是编辑前的数据
      ebooks.value = [];
      axios
        .get('/ebook/list', {
          params: {
            page: params.page,
            size: params.size,
          },
        })
        .then(response => {
          loading.value = false;
          const data = response.data;
          if (data.success) {
            ebooks.value = data.content.list;

            // 重置分页按钮
            pagination.value.current = params.page;
            pagination.value.total = data.content.total;
          } else {
            message.error(data.message);
          }
        });
    };
    const handleTableChange = (pagination: any) => {
      console.log('自带的分页参数：' + pagination);
      handleQuery({
        page: pagination.current,
        size: pagination.pageSize,
      });
    };

    /* 弹框代码 */
    const modalVisible = ref(false);
    const modalLoading = ref(false);
    const handleModalOk = () => {
      modalLoading.value = true;
      setTimeout(() => {
        modalLoading.value = false;
        modalVisible.value = false;
      }, 2000);
    };
    const edit = () => {
      modalVisible.value = true;
    };

    return { columns, loading, pagination, ebooks, modalVisible, modalLoading, handleModalOk, handleTableChange, edit };
  },
});
</script>

<style scoped></style>
