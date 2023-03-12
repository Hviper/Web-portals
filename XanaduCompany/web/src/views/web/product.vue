<template>
  <div>
    <AwHeader class = "job-header" ref = "news-header"></AwHeader>
    <div class = "content-container">
      <div class = "jobDetail">
        <h1>{{ jobDetail.title }}</h1>
        <div class = "job-info">
          <span>2020年09月03日 10:20:00</span>
          <div class = "lineDivider"></div>
          <!-- <span>2020年09月03日 10:20:00</span> -->
        </div>
        <div class = "description">
          <h2>详细信息</h2>
          <mavon-editor
            ref = "description"
            class = "markdown"
            v-model = "jobDetail.content"
            :subfield = "false"
            :boxShadow = "false"
            defaultOpen = "preview"
            :toolbarsFlag = "false"
          />
        </div>
        <!-- 职位要求暂时不需要 -->
        <!-- <div class = "requirement">
          <h2>职位要求</h2>
          <mavon-editor
            ref = "requirement"
            class = "markdown"
            v-model = "jobDetail.requirement"
            :subfield = "false"
            :boxShadow = "false"
            defaultOpen = "preview"
            :toolbarsFlag = "false"
          />
        </div> -->
      </div>
    </div>
    <AwFooter></AwFooter>
  </div>
</template>

<script>
import AwHeader from '../../components/web/public/Header'
import AwFooter from '../../components/web/public/Footer'

export default {
  name: 'product',
  components: {
    AwHeader,
    AwFooter
  },
  data () {
    return {
      jobDetail: {
        title:"电子与信息工程学院简介",
        content:`
　　   电子与信息工程学院设有计算机科学与技术系、电子工程系、信息工程系，开办计算机科学与技术、软件工程、电子信息科学与技术、通信工程、网络工程、数据科学与大数据技术六个本科专业，全日制在校学生1900余人。电子信息科学与技术是江西省卓越工程师培养计划专业，计算机科学与技术是江西省特色专业和校级卓越工程师培养计划专业。计算机应用技术为江西省重点学科，电子信息为学校高峰学科，信息与通信工程为学校高原学科。

　　　 全院现有教职工96人，专任教师76人，其中教授9人、副教授32人；具有博士学位教师23人，具有硕士学位教师47人；有国务院政府特殊津贴1人，江西省“百千万人才”4人，江西省教学名师2人，江西省高层次人才“千人计划”1人，江西省高校中青年学科带头人和骨干教师4人，江西省杰出青年人才1人。

　　　 学院建有流域生态与地理环境监测国家测绘地理信息局重点实验室、江西省农作物生长物联网技术工程实验室、教育部-中兴通讯ICT产教融合创新基地；设有计算机应用技术研究所、数字生态和空间信息研究中心、吉安市单片机与嵌入式系统重点实验室、吉安市智能信息感知处理与应用工程技术研究中心等多个省部、市级教学科研平台。建有电子技术实验室、信息技术实验室、计算机技术实验室,下设模电/数电实验室、嵌入式系统实验室、物联网实训室、软件基础实验室、网络基础实验室等教学实验分室。

　　　 近年来，学院教师共获得国家自然科学基金21项，国家科技支撑项目2项，省级项目68项，在国内外学术刊物发表科研论文220余篇，其中SCI收录23篇，EI收录28篇，A类58篇；获批专利与软件著作权251项，其中发明专利18项、实用新型专利56项、软件著作权117项,其它知识产权成果4项；出版专著和教材17部。

　　　 学院依托教育部-中兴通讯ICT产教融合创新基地，在通信工程、计算机科学与技术等专业进行高端人才的联合培养，并与江西省电子信息产业基地的多家企业建立联合实验室和联合培养学生，在省内外20多家企业建立了实习基地。学院重点培养学生实践与创新能力，近三年来学生先后获国家级科技竞赛奖项19项、省级奖项166项，奖项覆盖“挑战杯”全国大学生课外学术科技作品竞赛和创业计划大赛、全国信息技术应用水平大赛、全国机器人大赛、江西省电子电脑大赛、江西省手机软件设计大赛等。每年暑假组织学生参加社会实践，连续多年获省级表彰。积极开展学生文体活动，连续多年荣获校运会团体总分第一名和体育道德风尚奖。学院团委多次荣获省级和校级“五四红旗团委”光荣称号。

　　　 学院与同济大学、武汉大学开展了人才培养合作，每年有2-3位教师赴同济大学进修或攻读博士学位，每年选拔8-10位本科生分别到同济大学电子与信息工程学院、武汉大学计算机学院、厦门大学电子信息科学与技术、南京信息工程大学计算机科学与技术和软件工程进行联合培养。学院重视国际交流与合作，与英国、澳大利亚、韩国等高校开展多种模式的人才培养合作，与台湾的大学开展交换生项目，每年派出若干位学生赴台湾交流学习。

　　　 学院立足于办学所在地吉安市的“江西省电子信息产业基地”，背靠江西京九电子信息产业带，面向社会需求，加快应用转型，加强校企合作，创新电子、通信、计算机、软件、网络、大数据等专业的人才培养模式，提升科学研究和技术开发水平，努力培养能适应国家电子信息产业发展需要、具有创新和实践能力的卓越应用型人才。`
      }
    }
  },
  computed: {
    job_path () {
      return this.$route.params.id
    }
  },
  created () {
    this.getJobDetail()
  },
  mounted () {
    this.$bus.$on('jobData',this.jobData)
    this.$store.commit('setHeaderLogo', {
      headerLogoShow: false
    })
    this.$store.commit('setShadowActive', {
      headerShadowActive: true
    })
    this.$store.commit('setNavDarkActive', {
      navDarkActive: true
    })
    this.$store.commit('setHeaderShow', {
      headerShow: false
    })
  },
  beforeRouteLeave (to, from, next) {
    // 导航离开该组件的对应路由时调用
    // 可以访问组件实例 `this`
    if (to.name === 'index') {
      this.$store.commit('setHeaderLogo', {
        headerLogoShow: true
      })
      this.$store.commit('setShadowActive', {
        headerShadowActive: false
      })
      this.$store.commit('setNavDarkActive', {
        navDarkActive: false
      })
      this.$store.commit('setHeaderShow', {
        headerShow: false
      })
      next()
    } else {
      next()
    }
  },
  methods: {
    Delivery(){
      this.$message.success("正在打开邮箱，请稍等")
    },
    jobData(data){
      this.jobDetail = data
      console.log(data)
      //最终data传递到这里来
    },
    // 获取热职位详情
    async getJobDetail () {
      const { data: res } = await this.$http.get('/web/job/' + this.job_path)
      // console.log(res)
      if (res.status !== 200) {
        console.log(res)
      } else {
        this.jobDetail = {
          title: res.data.title,
          city: res.data.city,
          description: res.data.description,
          requirement: res.data.requirement,
          job_category: res.data.job_category,
          publish_time: res.data.publish_time,
          update_time: res.data.update_time
        }
      }
    }
  }
}
</script>

<style lang = "less" scoped>
.job-header {
  background-color: rgba(255, 255, 255, .7);
  backdrop-filter: blur(10px);
}

.content-container {
  padding-top: 60px;
  width: 100%;

  * {
    box-sizing: border-box
  }
}

h1 {
  font-size: 30px;
  font-weight: 800;
  color: #1f2329;
  margin-bottom: 13px;
}

.job-info, .update-time {
  display: flex;
  -ms-flex-align: center;
  align-items: center;
  color: #646a73;
  font-size: 14px;
  font-weight: 500;
  line-height: 20px;
}

.update-time {
  margin-top: 20px;
  margin-bottom: 20px;
}
a, a:hover, a:active, a:visited, a:link, a:focus {
  outline: none;
  background: none;
  text-decoration: none;
  color: #FFFFFF;
}
.lineDivider {
  display: inline-block;
  width: 1px;
  height: 12px;
  background-color: #bbbfc4;
  margin: 0 8px;
}

h2 {
  margin: 50px 0 8px;
  font-size: 21px;
  line-height: 1.52;
  font-weight: 800;
  color: #1f2329;
}

.jobDetail {
  font-size: 16px;
  max-width: 800px;
  min-height: 573px;
  margin: 60px auto 110px;
  padding-left: 32px;

  .markdown {
    position: relative;
    z-index: 0;
    border: none !important;

    ::v-deep .v-show-content {
      background-color: #FFFFFF !important;
      padding: 0 !important;
    }
  }

  ::v-deep .v-note-wrapper {
    min-height: 50px;
  }
}
</style>
