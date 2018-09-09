package com.zos.coupon.app.resource;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.zos.security.rbac.domain.QUser;
import com.zos.security.rbac.domain.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableJpaAuditing
@SpringBootApplication
@EnableTransactionManagement
@EnableAsync(proxyTargetClass = true)
@ComponentScan(basePackages = {"com.zos"})
public class CouponAppResourceApplication {

	/*//@Autowired
	//SecurityProperties securityProperties;
	
	@Autowired
	UserRepository userRepository;*/

	public static void main(String[] args) {
		SpringApplication.run(CouponAppResourceApplication.class, args);
	}
/*
	@GetMapping("/me")
	public Object getCurrentUser(Authentication user, HttpServletRequest request) {

		String token = StringUtils.substringAfter(request.getHeader("Authorization"), "bearer ");

		Claims claims = null;
		try {
			claims = Jwts.parser().setSigningKey(securityProperties.getOauth2().getJwtSigningKey().getBytes("UTF-8"))
                        .parseClaimsJws(token).getBody();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		String company = (String) claims.get("company");

		System.out.println(company);

		return user.getPrincipal();
	}

	@GetMapping("/hi")
	public String getHello() {
		return "hello";
	}
	
	@Autowired
    private EntityManager entityManager;

    // JPA查询工厂
    // private JPAQueryFactory queryFactory;
	
	// 实体管理者
    @Autowired
    private EntityManager entityManager;

    // JPA查询工厂
    private JPAQueryFactory queryFactory;

    
     * PostConstruct 类似于Serclet的inti()方法, 被PostConstruct修饰的方法会在构造函数之后, init()方法之前运行
     * PreDestroy 类似于Servlet的destroy()方法, 被PreDestroy修饰的方法会在destroy()方法之后运行, 在Servlet被彻底卸载之前
     *
    @PostConstruct
    public void initFactory() {
        queryFactory = new JPAQueryFactory(entityManager);
        log.info("init JPAQueryFactory successfully");
    }
    
    ////////////////////////////////////////////
    ////
    ////    单表操作部分
    ////
    ///////////////////////////////////////////

    
    @GetMapping(value = "/queryAll")
    public List<User> queryAll() {
        //使用querydsl查询
        QUser _Q_user = QUser.user;
        //查询并返回结果集
        return queryFactory
                .selectFrom(_Q_user)//查询源
                .orderBy(_Q_user.id.desc())//根据id倒序
                .fetch();//执行查询并获取结果集
    }
    
    
    @GetMapping(value = "/detail/{id}")
    public User detail(@PathVariable("id") Long id) {
        //使用querydsl查询
        QUser _Q_user = QUser.user;
        //查询并返回结果集
        return queryFactory
                .selectFrom(_Q_user)//查询源
                .where(_Q_user.id.eq(id))//指定查询具体id的数据
                .fetchOne();//执行查询并返回单个结果
    }
    
    
    @GetMapping(value = "/detail_2/{id}")
    public User detail_2(@PathVariable("id") Long id) {
        //使用querydsl查询
        QUser _Q_user = QUser.user;
        //查询并返回指定id的单条数据
        return userRepository.findOne(_Q_user.id.eq(id));
    }
    
    
    @GetMapping(value = "/likeQueryWithName")
    public List<User> likeQueryWithName(String name) {
        //使用querydsl查询
        QUser _Q_user = QUser.user;

        return queryFactory
                .selectFrom(_Q_user)//查询源
                .where(_Q_user.name.like(name))//根据name模糊查询
                .fetch();//执行查询并返回结果集
    }
    
    
    @Transactional
    @PutMapping(value = "/updateWithJpa")
    public String updateWithJpa(@RequestBody User user) {
        // 保存会员信息相当于Hibernate内的SaveOrUpdate
        userRepository.save(user);
        return "SUCCESS";
    }
    
    
    @Transactional
    @PutMapping(value = "/updateWithQueryDsl")
    public String updateWithQueryDsl(@RequestBody User user) {
        // querydsl查询实体
        QUser _Q_user = QUser.user;

        queryFactory
                .update(_Q_user)//更新对象
                //更新字段列表
                .set(_Q_user.name,user.getName())
                .set(_Q_user.address,user.getAddress())
                .set(_Q_user.age,user.getAge())
                .set(_Q_user.pwd,user.getPwd())
                //更新条件
                .where(_Q_user.id.eq(user.getId()))
                //执行更新
                .execute();
        return "SUCCESS";
    }
    
    
    @Transactional
    @DeleteMapping(value = "/deleteWithJpa")
    public String deleteWithJpa(User user) {
        //执行删除指定主键的值
        userRepository.delete(user.getId());
        return "SUCCESS";
    }
    
    
    @Transactional
    @DeleteMapping(value = "/deleteWithQueryDsl")
    public String deleteWithQueryDsl(User user) {
        //querydsl查询实体
        QUser _Q_user = QUser.user;

        queryFactory
                //删除对象
                //.delete(_Q_user)
                //删除条件
                //.where(_Q_user.id.eq(user.getId()))
                //执行删除
                //.execute();
        
		        //删除对象
		        .delete(_Q_user)
		        //删除条件
		        .where(
	                _Q_user.name.eq(user.getName())
	                .and(_Q_user.age.gt(20))
		        )
		        //执行删除
		        .execute();
        return "SUCCESS";
    }
    
    ////////////////////////////////////////////
    ////
    ////    多表操作部分
    ////
    ///////////////////////////////////////////
    
    @GetMapping(value = "/selectByType")
    public List<GoodInfo> selectByType(@RequestParam(value = "typeId") Long typeId //类型编号
            ){
        //商品查询实体
        QGoodInfo _Q_good = QGoodInfo.goodInfo;
        //商品类型查询实体
        QGoodType _Q_good_type = QGoodType.goodType;
        return
                queryFactory
                .select(_Q_good)
                .from(_Q_good,_Q_good_type)
                .where(
                        //为两个实体关联查询
                        _Q_good.typeId.eq(_Q_good_type.id)
                        .and(
                                //查询指定typeid的商品
                                _Q_good_type.id.eq(typeId)
                        )
                )
                //根据排序字段倒序
                .orderBy(_Q_good.order.desc())
                //执行查询
                .fetch();
    }
    
    
    @GetMapping(value = "/selectWithQueryDSL")
    public List<GoodDTO> selectWithQueryDSL() {
        //商品基本信息
        QGoodInfo _Q_good = QGoodInfo.goodInfo;
        //商品类型
        QGoodType _Q_good_type = QGoodType.goodType;

        return queryFactory
                .select(
                        Projections.bean(
                                GoodDTO.class,//返回自定义实体的类型
                                _Q_good.id,
                                _Q_good.price,
                                _Q_good.title,
                                _Q_good.unit,
                                _Q_good_type.name.as("typeName"),//使用别名对应dto内的typeName
                                _Q_good_type.id.as("typeId")//使用别名对应dto内的typeId
                         )
                )
                .from(_Q_good,_Q_good_type)//构建两表笛卡尔集
                .where(_Q_good.typeId.eq(_Q_good_type.id))//关联两表
                .orderBy(_Q_good.order.desc())//倒序
                .fetch();
    }
    
    
    @GetMapping(value = "/selectWithStream")
    public List<GoodDTO> selectWithStream() {
        //商品基本信息
        QGoodInfo _Q_good = QGoodInfo.goodInfo;
        //商品类型
        QGoodType _Q_good_type = QGoodType.goodType;
        return queryFactory
                .select(
                        _Q_good.id,
                        _Q_good.price,
                        _Q_good.title,
                        _Q_good.unit,
                        _Q_good_type.name,
                        _Q_good_type.id
                )
                .from(_Q_good,_Q_good_type)//构建两表笛卡尔集
                .where(_Q_good.typeId.eq(_Q_good_type.id))//关联两表
                .orderBy(_Q_good.order.desc())//倒序
                .fetch()
                .stream()
                //转换集合内的数据
                .map(tuple -> {
                    //创建商品dto
                    GoodDTO dto = new GoodDTO();
                    //设置商品编号
                    dto.setId(tuple.get(_Q_good.id));
                    //设置商品价格
                    dto.setPrice(tuple.get(_Q_good.price));
                    //设置商品标题
                    dto.setTitle(tuple.get(_Q_good.title));
                    //设置单位
                    dto.setUnit(tuple.get(_Q_good.unit));
                    //设置类型编号
                    dto.setTypeId(tuple.get(_Q_good_type.id));
                    //设置类型名称
                    dto.setTypeName(tuple.get(_Q_good_type.name));
                    //返回本次构建的dto
                    return dto;
                })
                //返回集合并且转换为List<GoodDTO>
                .collect(Collectors.toList());
    }
    
    
    @GetMapping(value = "/countExample")
    public long countExample() {
        //用户查询实体
        QUser _Q_user = QUser.user;
        return queryFactory
                .select(_Q_user.id.count())//根据主键查询总条数
                .from(_Q_user)
                .fetchOne();//返回总条数
    }
    
    
    @GetMapping(value = "/sumExample")
    public double sumExample() {
        //用户查询实体
        QUser _Q_user = QUser.user;
        return queryFactory
                .select(_Q_user.socre.sum())//查询积分总数
                .from(_Q_user)
                .fetchOne();//返回积分总数
    }
    
    
    @GetMapping(value = "/avgExample")
    public double avgExample() {
        //用户查询实体
        QUser _Q_user = QUser.user;
        return queryFactory
                .select(_Q_user.socre.avg())//查询积分平均值
                .from(_Q_user)
                .fetchOne();//返回平均值
    }
    
    
    @GetMapping(value = "/maxExample")
    public double maxExample() {
        //用户查询实体
        QUser _Q_user = QUser.user;
        return queryFactory
                .select(_Q_user.socre.max())//查询最大积分
                .from(_Q_user)
                .fetchOne();//返回最大积分
    }
    
    
    @GetMapping(value = "/groupByExample")
    public List<User> groupByExample() {
        //用户查询实体
        QUser _Q_user = QUser.user;
        return queryFactory
                .select(_Q_user)
                .from(_Q_user)
                .groupBy(_Q_user.socre)//根据积分分组
                .having(_Q_user.age.gt(22))//并且年龄大于22岁
                .fetch();//返回用户列表
    }
    
    
    @GetMapping(value = "/childLikeSelect")
    public List<GoodInfo> childLikeSelect() {
        //商品基本信息查询实体
        QGoodInfo _Q_good = QGoodInfo.goodInfo;
        //商品类型查询实体
        QGoodType _Q_good_type = QGoodType.goodType;

        return queryFactory
                .selectFrom(_Q_good)//查询商品基本信息表
                .where(
                        //查询类型名称包含“蔬菜”
                        _Q_good.typeId.in(
                                JPAExpressions.select(
                                        _Q_good_type.id
                                )
                                .from(_Q_good_type)
                                .where(_Q_good_type.name.like("%蔬菜%"))
                        )
                ).fetch();
    }
    
    
    @GetMapping(value = "/childEqSelect")
    public List<GoodInfo> childEqSelect() {
        //商品基本信息查询实体
        QGoodInfo _Q_good = QGoodInfo.goodInfo;

        return queryFactory
                .selectFrom(_Q_good)
                //查询价格最大的商品列表
                .where(_Q_good.price.eq(
                        JPAExpressions.select(
                                _Q_good.price.max()
                        )
                        .from(_Q_good)
                ))
                .fetch();
    }
    
    
    @GetMapping(value = "/childGtAvgSelect")
    public List<GoodInfo> childGtAvgSelect() {
        //商品基本信息查询实体
        QGoodInfo _Q_good = QGoodInfo.goodInfo;
        return queryFactory
                .selectFrom(_Q_good)
                //查询价格高于平均价的商品列表
                .where(
                        _Q_good.price.gt(
                                JPAExpressions.select(_Q_good.price.avg())
                                .from(_Q_good)
                        )
                ).fetch();
    }
    
    @Transactional
	@GetMapping(value = "/usertest")
	public void getUser() {
    	User user = new User();
    	user.setUsername("2");
    	entityManager.persist(user);
    	Role role = new Role();
    	role.setName("2");
    	entityManager.persist(role);
    	UserRole userRole = new UserRole();
    	userRole.setUser(user);
    	userRole.setRole(role);
    	entityManager.persist(userRole);
    	user = entityManager.find(User.class, 14L);
    	
    	role = entityManager.find(Role.class, role.getId());
    	//entityManager.persist(userRole);
    	log.info("size: "+user.getUserRoles().size());
    	log.info("size: "+role.getUserRoles().size());
    	//entityManager.remove(role);
    	//user.getUserRoles();
    	//entityManager.persist(user);
//    	role.getUserRoles().forEach(action -> {
//    		entityManager.remove(action);
//    	});
	}*/
	


    // JPA查询工厂
    private JPAQueryFactory queryFactory;
	
	@GetMapping(value = "/detail/{id}")
    public User detail(@PathVariable("id") Long id) {
        //使用querydsl查询
        QUser _Q_user = QUser.user;
        //查询并返回结果集
        return queryFactory
                .selectFrom(_Q_user)//查询源
                .where(_Q_user.id.eq(id))//指定查询具体id的数据
                .fetchOne();//执行查询并返回单个结果
    }
    
}
