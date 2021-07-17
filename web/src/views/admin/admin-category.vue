<!--
 * @Author: GZH
 * @Date: 2021-07-14 20:11:02
 * @LastEditors: GZH
 * @LastEditTime: 2021-07-17 14:32:54
 * @FilePath: \web\src\views\admin\admin-category.vue
 * @Description: 
-->
<template>
  <a-layout>
    <a-layout-content :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }">
      <p>
        <a-form layout="inline" :model="param">
          <a-form-item>
            <a-input v-model:value="param.name" placeholder="名称"></a-input>
          </a-form-item>
          <a-form-item>
            <a-button type="primary" @click="handleQuery({ page: 1, size: pagination.pageSize })">查询</a-button>
          </a-form-item>
          <a-form-item>
            <a-button type="primary" @click="add()">新增</a-button>
          </a-form-item>
        </a-form>
      </p>
      <a-table
        :columns="columns"
        :row-key="record => record.id"
        :data-source="categorys"
        :pagination="pagination"
        :loading="loading"
        @change="handleTableChange"
      >
        <template #cover="{ text: cover }">
          <img v-if="cover" :src="cover" alt="avatar" class="img-cover" />
        </template>

        <template v-slot:action="{ record }">
          <a-space size="small">
            <a-button type="primary" @click="edit(record)">
              编辑
            </a-button>

            <a-popconfirm
              title="删除后不可恢复，确认删除?"
              ok-text="是"
              cancel-text="否"
              @confirm="handleDelete(record.id)"
            >
              <a-button type="danger">删除</a-button>
            </a-popconfirm>
          </a-space>
        </template>
      </a-table>
    </a-layout-content>
  </a-layout>
  <a-modal title="分类" v-model:visible="modalVisible" :comfirm-loading="modalLoading" @ok="handleModalOk">
    <a-form :model="category" :label-col="{ span: 6 }" :wrapper-col="{ span: 18 }">
      <a-form-item label="名称">
        <a-input v-model:value="category.name" />
      </a-form-item>
      <a-form-item label="父分类">
        <a-input v-model:value="category.parent" />
      </a-form-item>
      <a-form-item label="顺序">
        <a-input v-model:value="category.sort" />
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<script lang="ts">
import axios from 'axios';
import { defineComponent, onMounted, ref } from 'vue';
import { message } from 'ant-design-vue';
import { Tool } from '@/util/tool';

export default defineComponent({
  name: 'adminCategory',
  setup() {
    const param = ref({ name: '' });
    const loading = ref(false);
    const pagination = ref({
      current: 1,
      pageSize: 10,
      total: 0,
    });
    const categorys = ref();

    const columns = [
      {
        title: '名称',
        dataIndex: 'name',
      },
      {
        title: '父分类',
        key: 'parent',
        dataIndex: 'parent',
      },
      {
        title: '顺序',
        dataIndex: 'sort',
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
      categorys.value = [];
      axios
        .get('/category/list', {
          params: {
            page: params.page,
            size: params.size,
            name: param.value.name,
          },
        })
        .then(response => {
          loading.value = false;
          const data = response.data;
          if (data.success) {
            categorys.value = data.content.list;

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
    const category = ref();
    const modalVisible = ref(false);
    const modalLoading = ref(false);
    const handleModalOk = () => {
      axios.post('/category/save', category.value).then(response => {
        modalLoading.value = false;
        const data = response.data; // data = commonResp
        if (data.success) {
          modalVisible.value = false;

          // 重新加载列表
          handleQuery({
            page: pagination.value.current,
            size: pagination.value.pageSize,
          });
        } else {
          message.error(data.message);
        }
      });
    };
    const edit = (record: any) => {
      modalVisible.value = true;
      category.value = Tool.copy(record);
      // category.value = record;
    };

    /* 新增 */
    const add = () => {
      modalVisible.value = true;
      category.value = {};
    };

    /* 删除 */
    const handleDelete = (id: number) => {
      axios.delete('/category/delete/' + id).then(response => {
        const data = response.data; // data = commonResp
        if (data.success) {
          // 重新加载列表
          handleQuery({
            page: pagination.value.current,
            size: pagination.value.pageSize,
          });
        } else {
          message.error(data.message);
        }
      });
    };
    return {
      param,
      columns,
      loading,
      pagination,
      categorys,
      category,
      modalVisible,
      modalLoading,
      handleModalOk,
      handleTableChange,
      edit,
      add,
      handleDelete,
      handleQuery,
    };
  },
});
</script>

<style scoped>
.img-cover {
  width: 100px;
  height: 100px;
}
</style>
