<template>
  <div class="job">
    <AwHeader class="job_header" ref="job_header"></AwHeader>
    <div class="box">
      <div class="banner">和优秀的人，做有挑战的事</div>
      <!-- 搜索 -->
      <div class="search-wrapper" :class="{ fixedTop: searchBarFixedTop }">
        <el-input
          :class="[{ medium: searchBarFixedTop }, 'small']"
          placeholder="搜索关键字"
          @change="search"
          v-model="searchKeyword"
        >
          <i slot="prefix" class="el-input__icon el-icon-search"></i>
        </el-input>
        <el-button
          :class="[{ medium: searchBarFixedTop }, 'small']"
          type="primary"
          round
          @click="search"
          >搜索
        </el-button>
      </div>
      <div class="main">
        <div class="aside-filter">
          <div class="aside-header">
            <span>筛选</span>
            <span :class="{ clearable }" class="clear" @click="clearFilter"
              >清空</span
            >
          </div>
          <div class="job-category job-filter-block">
            <div class="title">职位类别</div>
            <el-tree
              ref="jobCategory"
              :data="jobCategories"
              :props="jobCategoryProps"
              node-key="id"
              check-on-click-node
              @check="jobCategoryChange"
              show-checkbox
            >
            </el-tree>
          </div>
          <div class="city-category job-filter-block">
            <div class="title">城市</div>
            <checkbox-and-dropdown
              @changed="cityChange"
              :data="jobCities"
              :cityList="this.location_code_list"
              >{{ location_code_list }}
            </checkbox-and-dropdown>
          </div>
        </div>
        <div class="content">
          <h2 class="content-title" >就业咨询</h2>
          <ul class="content-list">
            <li
              class="content-item is-hover-shadow"
              v-for="item in results"
              :key="item.id"
            >
              <!-- :to = "`/job/${item.id}`" -->
              <div @click="dumpToJobDetail(item)">
                <h3 class="title">{{ item.title }}</h3>
                <div class="subTitle">
                  <!-- <span class = "city">{{ item.id }}</span>&nbsp;| -->
                  <!-- 字符串分割方法 -->
                  <span class="job_category"
                    >{{ item.content.substr(0, 100) }}.......</span
                  >
                </div>
                <p class="desc">{{ item.description }}</p>
              </div>
            </li>
          </ul>
          <!-- results长度为0不显示  -->
          <div v-show="!loading && results.length!=0 " class="pagination-wrapper">
            <el-pagination
              layout="prev, pager, next"
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page.sync="currentPage"
              :total="count"
              :hide-on-single-page="singlePage"
            >
            </el-pagination>
          </div>
        </div>
      </div>
    </div>
    <AwFooter></AwFooter>
  </div>
</template>

<script>
import AwHeader from "../../components/web/public/Header";
import AwFooter from "../../components/web/public/Footer";
import CheckboxAndDropdown from "../../components/web/CheckboxAndDropdown";
// import CheckboxTransfer from '../../components/web/Checkbox-Transfer'

export default {
  name: "job",
  components: {
    CheckboxAndDropdown,
    AwFooter,
    AwHeader,
    // CheckboxTransfer
  },
  methods: {
    handleSizeChange(val) {
      this.queryFilter.count = val;
      this.getJobList();
    },
    handleCurrentChange(val) {
      this.queryFilter.page = val;
      this.getJobList();
    },
    dumpToJobDetail(item) {
      this.$router.push("/job/" + item.id);
      setTimeout(() => {
        this.$bus.$emit("jobData", item); //将item信息发送到jobData事件中
      }, 100);
    },
    // 请求职位列表
    async getJobList() {
      // console.log(this.queryFilter)
      this.results = [];
      const { data: res } = await this.$http.get("/employments/info", {
        params: this.queryFilter,
      });
      if (res.meta.code === 200) {
        // if (this.results.count !== res.count) {
        //   this.currentPage = 1
        // }

        this.count = res.data.count;
        this.results = res.data.data;
        this.loading = false;
        if (this.results.total <= this.results.limit) {
          this.singlePage = true;
        }
      }
    },
    scrollHandle() {
      this.scrollTop =
        document.documentElement.scrollTop ||
        window.pageYOffset ||
        document.body.scrollTop;
      // 滚动条滚动的距离
      // const scrollStep = this.scrollTop - this.oldScrollTop
      // 更新——滚动前，滚动条距文档顶部的距离
      this.oldScrollTop = this.scrollTop;
      if (this.scrollTop >= 350) {
        this.$store.commit("setHeaderShow", {
          headerShow: true,
        });
      } else {
        this.$store.commit("setHeaderShow", {
          headerShow: false,
        });
      }
      this.searchBarFixedTop = this.scrollTop >= 430;
    },
    async getJobListByName() {
      // searchKeyword
      this.results = [];
      const { data: res } = await this.$http.get("/employments/search?keyword="+this.searchKeyword);
      if (res.meta.code === 200) {
        // if (this.results.count !== res.count) {
        //   this.currentPage = 1
        // }

        this.count = res.data.count;
        this.results = res.data.data;
        this.loading = false;
        if (this.results.total <= this.results.limit) {
          this.singlePage = true;
        }
      }
    },
    search() {
      this.getJobListByName();
    },
    clearFilter() {
      if (this.job_category_id_list.length) {
        this.job_category_id_list = [];
        this.$refs.jobCategory.setCheckedKeys(this.job_category_id_list, {
          leafOnly: true,
        });
      }
      if (this.location_code_list.length) {
        this.location_code_list = [];
      }
      this.getJobList();
    },
    // 请求筛选条件
    async getJobConfigRequest() {
      const { data: res } = await this.$http.get("/web/job-filters");
      if (res.status === 200) {
        this.jobCities = res.data.city_list;
        this.jobCategories = res.data.job_type_list;
      }
    },

    jobCategoryChange() {
      this.job_category_id_list = this.$refs.jobCategory.getCheckedKeys({
        leafOnly: true,
      });
      // console.log(this.$refs.jobCategory.getCheckedKeys({ leafOnly: true }))

      // this.getJobList();
    },
    cityChange(value) {
      this.location_code_list = value;
      // console.log(this.location_code_list)
      this.getJobList();
    },
  },
  created() {
    this.getJobConfigRequest();
    this.getJobList();
  },
  data() {
    return {
      //总条数
      count: 0,
      jobCategories: [
        {
          id: 1,
          name: " 数据科学与大数据技术",
          info: `数据科学与大数据技术（理工类，本科四年，授工学学士学位）
专业特色：本专业注重培养学生的工程实践能力，使他们能够胜任大数据的分析、处理、开发和管理等工作，使他们成为大数据领域的复合型高级技术人才。
培养目标：本专业培养具有科学与人文精神、创新与创业精神和良好职业道德，掌握大数据处理和分析的基本理论、基本方法和基本技术，具有数据采集、存储、处理、分析和应用的基本能力，能够运用专业知识和技能解决实际问题，具备较高综合业务素质、创新与实践能力，能够胜任各行业大数据的分析、处理和开发工作，能够管理和维护大数据系统，成为大数据科学与工程领域的复合型高级技术人才。
主要课程：操作系统、计算机组成原理、数据库原理、计算机网络、Python语言程序设计、大数据算法、应用统计、数据挖掘、人工智能基础、大数据处理、大数据分析等。
就业方向：能在信息产业部门以及政府机关、企事业单位从事大数据技术研究、开发和管理，具有较强的创新和实践能力，毕业后还可攻读计算机科学等相关学科的硕士研究生。

`,
        },
        {
          id: 2,
          name: "通信工程",
          info: `通信工程（理工类，本科四年，授工学学士学位）

专业特色：适应面广，口径比较宽，是目前国家战略计划信息消费的主要专业。

培养目标：本专业培养掌握通信技术、通信系统和通信网络等方面的基本理论、

基础知识和基本技能，具有创新精神和创业能力，具备通信技术研究、设计、

开发、维护和应用基本能力。

主要课程：电路分析、模拟电子技术、数字电子技术、高频电子线路、信号与

系统、数字信号处理、通信原理、数字通信、计算机网络、电磁场与微波技术、

光纤通信、程控交换、数字图像处理、单片机原理与应用、电路CAD、卫星通信、

多媒体技术等。

就业方向：能在通信领域从事工程设计、技术管理、研究开发和教育培训等工作

的高层次工程技术人才。毕业后还可攻读信息与通信工程相关学科的硕士研究生。`,
        },
        {
          id: 3,
          name: "电子信息科学与技术",
          info: `电子信息科学与技术（理工类，本科四年，授工学学士学位）
专业特色：本专业以电路和信号处理、嵌入式系统设计和通信系统为专业
的发展方向，以数学类、电子技术类、信息类和通信类为主要课程体系，
以电路与系统实验室、EDA技术实验室、单片机和嵌入式系统等一批实验
室为支撑，以校外实习基地和校内创新实验室为补充的一个专业人才培养
体系。现已获批江西省高校2013年“卓越工程师培养计划”，并已实施。
培养目标：培养具备电子信息系统设计、研制和开发能力。
主要课程：大学物理、高等数学、模拟电路、数字电路、电路分析基础、
信号与系统、通信原理、微机原理、计算机网络、数字信号处理、数字图
像处理、语音信号处理、电磁场与电磁波、电子设计自动化等。
就业方向：在电子信息科学与技术及相关领域从事产品设计、技术开发、
技术管理以及教学、研究工作的专门人才。毕业后还可攻读电子科学与技术`,
        },
        {
          id: 4,
          name: "软件工程",
          info: `软件工程（理工类，本科四年，授工学学士学位）
专业特色：本专业学生主要学习计算机软件方面的基本理论和基本知
识，接受从事研究与开发计算机软件的基本训练，具有研究和开发计
算机应用系统和软件的基本能力。
培养目标：培养系统掌握计算机软件基本理论、基本知识和基本技能，
通过软件开发和工程管理方法的基本训练，获得软件系统研究和开发
的基本能力，具有良好的科学素养和创新意识，具有相关学科知识和
较高外语水平。
主要课程：计算机组成原理、数据结构、计算机网络、操作系统、软
件工程、C++程序设计、可视化建模与UML、Windows程序设计、
Java企业级开发技术、`,
        },
        {
          id: 5,
          name: "计算机科学与技术",
          info: `计算机科学与技术（理工类，本科四年，授工学学士学位）
专业特色：本专业为江西省特色专业，并列入江西省第一批次本科（一本）录取专业。本专业学生接受从事研究与开发计算机软件的基本训练，具有本专业的专业知识和专业技能，了解本专业学科前沿和发展趋势，了解相关的工程技术的最新发展。具有本专业所必须的绘图、运算、实验、测试、开发、设计、计算机应用等技能以及一定的基本工艺操作技能。
培养目标：培养具有良好科学素养，系统掌握计算机科学与技术，包括计算机硬件、软件基本理论、基本知识和基本技能与方法，熟悉计算机操作与网络技术，具有开发计算机软件和硬件系统基本能力。
主要课程：C语言程序设计、汇编语言、计算机组成原理、数据结构与算法分析、离散数学、数字逻辑、电路分析与电子技术、操作系统、数据库原理、单片机、面向对象程序设计、计算机网络与通信、软件工程、计算机体系结构、编译原理、计算机图形学等。
就业方向：能在企事业单位和各行业从事计算机应用、开发、教育和研究的计算机科学与技术学科的专门人才。毕业后也可攻读计算机及相关学科的硕士研究生。`,
        },
      ],
      jobCategoryProps: {
        children: "children",
        label: "name",
      },
      jobCities: [
        { id: 1, name: "北京" },
        { id: 2, name: "上海" },
        { id: 3, name: "深圳" },
        { id: 4, name: "杭州" },
        { id: 5, name: "厦门" },
        { id: 6, name: "重庆" },
        { id: 7, name: "成都" },
        { id: 8, name: "南京" },
        { id: 9, name: "苏州" },
        { id: 10, name: "西安" },
        { id: 11, name: "青岛" },
      ],
      job_category_id_list: [],
      location_code_list: [],
      searchBarFixedTop: false,

      searchKeyword: "",
      currentPage: 1,
      pageSize: 4,
      cityList: [],
      cities: [],
      results: [],
      loading: false,
      locationCodeProps: {
        label: "name",
      },
      // 单页隐藏
      singlePage: "",
      // 城市是否勾选
      checked: false,
    };
  },
  computed: {
    queryFilter() {
      return {
        // job_category_id_list: this.job_category_id_list,
        // location_code_list: this.location_code_list,
        // keyword: this.searchKeyword,
        page: this.currentPage,
        count: this.pageSize,
      };
    },
    clearable() {
      return (
        this.job_category_id_list.length !== 0 ||
        this.location_code_list.length !== 0
      );
    },
  },

  mounted() {
    this.$store.commit("setHeaderLogo", {
      headerLogoShow: false,
    });
    this.$store.commit("setShadowActive", {
      headerShadowActive: false,
    });
    this.$store.commit("setNavDarkActive", {
      navDarkActive: true,
    });
    this.$store.commit("setHeaderShow", {
      headerShow: false,
    });
    window.addEventListener("scroll", this.scrollHandle); // 绑定页面的滚动事件
  },
  beforeDestroy() {
    window.removeEventListener("scroll", this.scrollHandle);
  },
  beforeRouteLeave(to, from, next) {
    // 导航离开该组件的对应路由时调用
    // 可以访问组件实例 `this`
    if (from.name === "job") {
      this.$store.commit("setNavDarkActive", {
        navDarkActive: false,
      });
      this.$store.commit("setHeaderLogo", {
        headerLogoShow: true,
      });
      next();
    }
  },
};
</script>

<style lang = "less" scoped>
.job_header {
  background-color: rgba(255, 255, 255, 1);
  backdrop-filter: blur(10px);
}

.box {
  padding-top: 60px;
  min-height: 860px;
  width: 100%;

  * {
    box-sizing: border-box;
  }

  .banner {
    height: 400px;
    line-height: 400px;
    color: #fff;
    width: 100%;
    min-width: @main-width;
    background-image: url("//sf1-ttcdn-tos.pstatp.com/obj/ttfe/ATSX/mainland/joblistbanner2x.jpg");
    background-size: cover;
    background-repeat: no-repeat;
    background-position: center;
    text-align: center;
    font-size: @font-size-larger;
  }
}

.search-wrapper {
  position: absolute;
  transform: translateY(-50%);
  width: 100%;

  &.fixedTop {
    position: fixed;
    box-shadow: 0 2px 16px 0 rgba(31, 35, 41, 5%);
    top: 0;
    transform: translateY(0);
    padding: 15px 0;
    z-index: 1000;
    background-color: #fff;
    transition: all 0.3s;
  }

  .el-input {
    width: 50%;
    left: 50%;
    transform: translateX(-50%);

    &.medium {
      width: 64%;
    }
  }

  /deep/ .el-input__inner {
    height: 50px;
    border-radius: 25px;
  }

  .el-button {
    position: absolute;
    line-height: 0;
    height: 50px;
    border-radius: 0 25px 25px 0;
    right: 25%;

    &.medium {
      right: 18%;
    }
  }
}

.main {
  width: 1026px;
  min-height: 400px;
  margin: 80px auto 60px;
  display: flex;

  .aside-filter {
    width: 275px;
    height: auto;
    padding-right: 35px;
    border-right: 1px solid @border-light-color;
  }

  .aside-header {
    font-size: 14px;
    font-weight: 700;
    color: #1f2329;
    border-bottom: 1px solid @border-lighter-color;
    padding-bottom: 12px;
    margin-top: 10px;
    margin-bottom: 23px;

    .clear {
      float: right;
      font-weight: normal;
      cursor: pointer;
    }

    .clearable {
      color: @main-color;
    }
  }

  .job-filter-block {
    margin-bottom: 39px;
    position: relative;
    height: auto;

    .title {
      font-size: 20px;
      font-weight: 700;
      color: @primary-text-color;
      margin-bottom: 9px;
    }
  }

  .content {
    width: 712px;
    height: auto;
    //margin-left: 300px;

    padding-left: 24px;
  }

  .content-title {
    font-size: 30px;
    line-height: 36px;
    height: 36px;
    font-weight: 700;
    margin-left: 16px;
    margin-bottom: 18px;
  }

  .content-list {
    margin-top: 22px;
  }

  .content-item {
    //margin-bottom: 20px;
    padding: 20px;
    transition: box-shadow 0.5s;

    &.is-hover-shadow:hover {
      box-shadow: 0 8px 24px 0 rgba(187, 191, 196, 0.2);
      border-radius: 5px;
    }

    .title {
      margin: 12px 0;
      font-size: 20px;
      font-weight: 600;
      line-height: 1.6;
    }

    .subTitle {
      flex-wrap: wrap;
      font-size: 16px;
      font-weight: 500;
      line-height: 1.5;
      color: #646a73;
      margin: 4px 0 12px;
    }

    .desc {
      width: 664px;
      max-height: 44px;
      font-size: 14px;
      font-weight: 500;
      font-style: normal;
      font-stretch: normal;
      line-height: 1.57;
      letter-spacing: normal;
      color: #8f959e;
      overflow: hidden;
      text-overflow: ellipsis;
      display: -webkit-box;
      white-space: pre-line;
      -webkit-line-clamp: 2;
    }
  }

  a {
    color: rgba(0, 0, 0, 1);
    overflow: hidden;
    cursor: pointer;
  }
}
</style>
<style lang = "less">
.el-tree {
  color: @primary-text-color;
  font-size: 14px;
  font-variant: tabular-nums;
  line-height: 1.5;
  list-style: none;
  -webkit-font-feature-settings: "tnum";
  font-feature-settings: "tnum";
  margin: 0;
  padding: 0;

  > .el-tree-node:first-child {
    margin-top: 8px;
  }

  .el-tree-node__content {
    height: 38px;
  }

  .el-tree-node__label {
    font-size: 16px;
  }

  .el-tree-node__expand-icon {
    color: #9ca2a9;
  }
}

.el-pagination {
  margin-top: 20px;
}
</style>
