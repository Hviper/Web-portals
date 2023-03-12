<template>
  <div class="wrap-block">
    <div class="content-block service-block">
      <div class="service-block">
        <div class="block-line"></div>
        <p class="service-info-title">快速探索</p>
        <!-- <p class = "service-info-subtitle">爱迪生</p> -->
        <div class="service-list">
          <div
            class="service-title"
            v-for="(item, index) in serviceList"
            :key="index"
          >
            <!-- @click="climb()" -->
            <div  @click="climb(item.description)">
              <div class="is-hover-shadow">
                <div class="service-title-shadow">
                  <img :src="item.path" alt="" />
                </div>
              </div>
              <p class="service-name">{{ item.description }}</p>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="content-block service-idea">
      <div class="service-idea">
        <div class="idea-left">
          <img :src="serviceIdeaImg[0].path" :alt="serviceIdeaImg[0].name" />
        </div>
        <div class="idea-right">
          <div class="block-line"></div>
          <p class="idea-title">{{ serviceIdeaInfo.ideaTitle }}</p>
          <p class="idea-subtitle">{{ serviceIdeaInfo.ideaSubtitle }}</p>
          <img :src="serviceIdeaImg[1].path" :alt="serviceIdeaImg[1].name" />
          <p class="idea-desc">{{ serviceIdeaInfo.ideaDesc }}</p>
          <router-link to="/news">
            <el-button type="primary" round>了解更多</el-button>
          </router-link>
        </div>
      </div>
    </div>
    <div class="content-block service-case">
      <div class="service-case">
        <div class="block-line"></div>
        <p class="case-title">党建思政</p>
        <p class="case-subtitle">致力建设特色政党型学院</p>
        <div class="case-list">
          <div class="case-info" v-for="(item, index) in caseList" :key="index">
            <el-card :body-style="{ padding: '0px' }" shadow="hover">
              <img :src="item.path" class="imgClass" :alt="item.desc" />
            </el-card>
            <el-progress :percentage="50" :show-text="false"></el-progress>
            <p class="case-desc">{{ item.desc }}</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "IndexService",
  methods: {
    climb(description) {
      if("新闻中心"===description){
        // router.push（相对路径）
        this.$router.push("/news");
      }else if("学院概况"===description){
        this.$router.push("/product");
      }
      else if("招生就业"===description){
        this.$router.push("/job");
      }
      else if("我的"===description){
        this.$router.push("/login");
      }
    },
  },
  data() {
    return {
      serviceList: [
        {
          description: "新闻中心",
          path: require("../../assets/img/index/bigData.webp"),
        },
        {
          description: "学院概况",
          path: require("../../assets/img/index/media.webp"),
        },
        {
          description: "招生就业",
          path: require("../../assets/img/index/internet.webp"),
        },
        {
          description: "我的", //跳转登录页面
          path: require("../../assets/img/index/cloud.webp"),
        },
      ],
      serviceIdeaInfo: {
        ideaTitle: "学科建设",
        ideaSubtitle: "Scientific Research",
        ideaDesc: `学院现有省级重点学科1个，校级重点学科2个，分别是: 
                  江西省重点学科：计算机应用技术,
                  井冈山大学重点学科：计算机软件与理论，计算机系统结构
                  `,
      },
      serviceIdeaImg: [
        {
          name: "lefImg",
          path: require("../../assets/img/index/service1.png"),
        },
        {
          name: "rightImg",
          path: require("../../assets/img/index/service2.png"),
        },
      ],
      caseList: [
        {
          desc: "党建动态",
          // path: require("../../assets/img/index/app1.jpg"),
          path: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSXJVr-epwFrdW_HUiqDUun9SPYqL4Onk_Wng&usqp=CAU",
        },
        {
          desc: "思想教育",
          path: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQwJKE_-B_requWNSqkSfL5jLc460ubk5lH5g&usqp=CAU",
          // path: require("../../assets/img/index/system1.jpg"),
        },
        // {
        //   desc: '私家智能定制APP', 
        //   path: require('../../assets/img/index/app1.jpg')
        // },
        {
          desc: "网上党校",
          path: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ9oj2WMbTxDagOOZWsEaSpLM2nM11FXfy1Rg&usqp=CAU",
          // path: require("../../assets/img/index/system2.jpg"),
        },
      ],
      percentage: "50",
    };
  },
};
</script>

<style lang = "less" scoped>
.imgClass{
  /* margin: 0 auto;
  text-align: center; */
  position: relative;
  padding-top: 20px;
}
.wrap-block {
  position: relative;
  padding-top: 54px;

  .content-block {
    position: relative;
    //width: 100%;
    overflow: hidden;
  }
}

.service-block {
  max-width: 1440px;
  height: 500px;
  margin: 0 auto;
  text-align: center;
  overflow-x: hidden;

  .block-line {
    margin: 0 auto 12px;
    width: 24px;
    height: 4px;
    background-color: #3370ff;
    border-radius: 100px;
  }

  .service-info-title {
    font-weight: 700;
    font-size: 32px;
    position: relative;
    bottom: 2px;
    //padding-top: 60px;
  }

  .service-info-subtitle {
    font-size: 16px;
    line-height: 1.5;
    color: #646a73;
    margin-top: 30px;
  }

  .service-list {
    display: flex;
    justify-content: space-evenly;
    text-align: center;
    //padding: 0 200px;
    margin: 70px auto 0 auto;
  }

  .service-title-shadow {
    width: 111px;
    height: 111px;
    border-radius: 50%;
    display: flex;
    justify-content: center;
    align-items: center;
    transition: 0.3s;
  }

  .service-title-shadow:hover {
    width: 111px;
    height: 111px;
    border-radius: 50%;
    transform: scale(1.05) translateY(-1px);
    box-shadow: 0 6px 20px 0 rgba(0, 0, 0, 0.15);
  }

  .service-name {
    margin-top: 30px;
    font-weight: 600;
    font-size: 20px;
  }
}

.content-block.service-idea {
  background-color: #f5f5f5;
  width: 100%;

  .service-idea {
    max-width: 1440px;
    //height: 700px;
    overflow: hidden;
    display: flex;
  }

  .idea-left img {
    width: 600px;
  }

  .idea-right {
    height: 500px;
    width: calc(100% - 600px);
    margin-left: 100px;
    padding-top: 50px;

    .block-line {
      margin: 0 0 12px 0;
      width: 24px;
      height: 4px;
      background-color: #3370ff;
      border-radius: 100px;
    }

    .idea-title {
      font-weight: 700;
      font-size: 32px;
      position: relative;
      bottom: 2px;
      //padding-top: 60px;
    }

    .idea-subtitle {
      font-size: 16px;
      line-height: 1.5;
      color: #646a73;
      margin-top: 10px;
    }

    img {
      width: 500px;
      height: 200px;
      object-fit: cover;
      margin-top: 40px;
      margin-bottom: 30px;
      padding: 10px;
      border: #d3dce6 2px dashed;
    }

    .idea-desc {
      display: -webkit-box;
      -webkit-box-orient: vertical;
      -webkit-line-clamp: 4;
      overflow: hidden;
      max-height: 88px;
      max-width: 550px;
      font-size: 14px;
      line-height: 30px;
      color: #646a73;
    }

    .el-button {
      margin-top: 40px;
    }
  }
}

.content-block.service-case {
  background-color: #ffffff;
  width: 100%;

  .service-case {
    max-width: 1440px;
    //height: 700px;
    overflow: hidden;
    margin: 0 auto;
    text-align: center;
    padding-top: 50px;

    .block-line {
      margin: 0 auto 12px;
      width: 24px;
      height: 4px;
      background-color: #3370ff;
      border-radius: 100px;
    }

    .case-title {
      font-weight: 700;
      font-size: 32px;
      position: relative;
      bottom: 2px;
      //padding-top: 60px;
    }

    .case-subtitle {
      font-size: 16px;
      line-height: 1.5;
      color: #646a73;
      margin-top: 10px;
    }

    .case-list {
      display: flex;
      justify-content: space-evenly;
    }

    .el-card {
      width: 247px;
      height: 217px;
      margin-top: 40px;
    }

    .el-progress {
      margin-top: 10px;
    }

    /deep/ .el-progress-bar__outer {
      height: 2px !important;
    }

    .case-desc {
      margin-top: 30px;
      margin-bottom: 60px;
      font-weight: 500;
      font-size: 14px;
    }
  }
}
</style>
