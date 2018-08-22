var gba = require('../../../utils/global.js')
Page({
  data: {
    imgUrl: gba.globalData.imgUrl, //图片地址
    // 是否使用优惠券
    isDisShow:true,
    // 是否置灰图案
    isGray:"",
    // 分类数据
    claBox:[
      { name: '未使用', state: 'active' },
      { name: '已使用', state: '' },
      { name: '已过期', state: '' }
    ],
    // 图标显示
    iconShow:0,
    // 当前选择的分类
    presentCla:[],
    // 未使用优惠券
    unused:[
      { price: "15", threshold: "无门槛", name: "新人专享", user: "限15566667777用户使用", period: "有效期2018.02.01至2018.03.01" },
      { price: "20", threshold: "满199-20", name: "新人专享", user: "限15566667777用户使用", period: "有效期2018.02.01至2018.03.01" },
      { price: "50", threshold: "满399-50", name: "新人专享", user: "限15566667777用户使用", period: "有效期2018.02.01至2018.03.01" }
    ],
    // 已过期优惠券
    expired: [
      { price: "5", threshold: "无门槛", name: "新人专享", user: "限15566667777用户使用", period: "有效期2018.02.01至2018.03.01" },
      { price: "10", threshold: "满199-10", name: "新人专享", user: "限15566667777用户使用", period: "有效期2018.02.01至2018.03.01" },
      { price: "15", threshold: "满399-15", name: "新人专享", user: "限15566667777用户使用", period: "有效期2018.02.01至2018.03.01" }
    ],
    // 已使用优惠券
    haveUnused: [
      { price: "30", threshold: "无门槛", name: "新人专享", user: "限15566667777用户使用", period: "有效期2018.02.01至2018.03.01" },
      { price: "50", threshold: "满199-50", name: "新人专享", user: "限15566667777用户使用", period: "有效期2018.02.01至2018.03.01" },
      { price: "70", threshold: "满399-70", name: "新人专享", user: "限15566667777用户使用", period: "有效期2018.02.01至2018.03.01" }
    ]
  },
  onLoad: function (options){
    this.setData({ presentCla: this.data.unused})
  },
  // 切换tab页面
  cutTab:function(e){
    var id=e.currentTarget.dataset.id;
    for(var i=0;i<this.data.claBox.length;i++){
      var key = 'claBox[' + i + '].state';
      var obj = {};
      obj[key] = "";
      this.setData(obj);
    }
    var key = 'claBox[' + id + '].state';
    var obj = {};
    obj[key] = "active";
    this.setData(obj);
    if(id==0){
      this.setData({ isGray: "", presentCla: this.data.unused, iconShow: 0 })
    }else if(id==1){
      this.setData({ isGray: "gray", presentCla: this.data.expired, iconShow: 1 })
    }else if(id==2){
      this.setData({ isGray: "gray", presentCla: this.data.haveUnused, iconShow: 2 })
    }
  },
  // 是否使用优惠券
  isDiscShow:function(e){
    var id = e.currentTarget.dataset.id;
    if(id==true){
      this.setData({ isDisShow: false});
    }else if(id==false){
      this.setData({ isDisShow: true});
    }
  }
})