<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
    <title>JRepetitor</title>
    <spring:url value="/resources/css/bootstrap.css" var="bootstrap"/>
    <spring:url value="/resources/css/modern-business.css" var="startertemplate"/>
    <%--<link href="${bootstrap}" rel="stylesheet" />--%>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <link href="${startertemplate}" rel="stylesheet" />
</head>
<body>

<!-- Navigation -->
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="index.html">Java Repetitor</a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <a href="http://localhost:8080/">Главная</a>
                </li>
                <li>
                    <a href="http://localhost:8080/queans">Материалы</a>
                </li>
                <li>
                    <a href="http://localhost:8080/user">Личный кабинет</a>
                </li>
                <li>
                    <a href="http://localhost:8080/user">Help</a>
                </li>
                <%--<li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Tutorial<b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="portfolio-1-col.html">Lesson 1</a>
                        </li>
                        <li>
                            <a href="portfolio-2-col.html">Lesson 2</a>
                        </li>
                        <li>
                            <a href="portfolio-3-col.html">Lesson 3</a>
                        </li>
                        <li>
                            <a href="portfolio-4-col.html">Lesson 5</a>
                        </li>
                        <li>
                            <a href="portfolio-item.html">Lesson 5</a>
                        </li>
                    </ul>
                </li>--%>
                <%--<li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Blog <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="blog-home-1.html">Blog Home 1</a>
                        </li>
                        <li>
                            <a href="blog-home-2.html">Blog Home 2</a>
                        </li>
                        <li>
                            <a href="blog-post.html">Blog Post</a>
                        </li>
                    </ul>
                </li>--%>
                <%--<li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false">Other Pages <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="full-width.html">Full Width Page</a>
                        </li>
                        <li>
                            <a href="sidebar.html">Sidebar Page</a>
                        </li>
                        <li>
                            <a href="faq.html">FAQ</a>
                        </li>
                        <li>
                            <a href="404.html">404</a>
                        </li>
                        <li>
                            <a href="pricing.html">Pricing Table</a>
                        </li>--%>
            </ul>
            </li>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container -->
</nav>

<!-- Header Carousel -->
<%--<header id="myCarousel" class="carousel slide">

  <ol class="carousel-indicators">
    <li data-target="#myCarousel" data-slide-to="0" class=""></li>
    <li data-target="#myCarousel" data-slide-to="1" class="active"></li>
    <li data-target="#myCarousel" data-slide-to="2"></li>
  </ol>


  <div class="carousel-inner">
    <div class="item">
      <div class="fill" style="background-image:url('http://placehold.it/1900x1080&amp;text=Slide One');"></div>
      <div class="carousel-caption">
        <h2>Caption 1</h2>
      </div>
    </div>
    <div class="item active">
      <div class="fill" style="background-image:url('http://placehold.it/1900x1080&amp;text=Slide Two');"></div>
      <div class="carousel-caption">
        <h2>Caption 2</h2>
      </div>
    </div>
    <div class="item">
      <div class="fill" style="background-image:url('http://placehold.it/1900x1080&amp;text=Slide Three');"></div>
      <div class="carousel-caption">
        <h2>Caption 3</h2>
      </div>
    </div>
  </div>


  <a class="left carousel-control" href="#myCarousel" data-slide="prev">
    <span class="icon-prev"></span>
  </a>
  <a class="right carousel-control" href="#myCarousel" data-slide="next">
    <span class="icon-next"></span>
  </a>
</header>--%>

<!-- Page Content -->
<br>
<div class="container">

    <!-- Marketing Icons Section -->
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">
                Мы научим вас программировать на JAVA
            </h1>
        </div>
        <div class="col-md-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4><i class="fa fa-fw fa-check"></i> Java0</h4>
                </div>
                <div class="panel-body">
                    <p>Java для начинающих! Даже ребенок пройдет этот уровень!</p>
                    <a href="http://localhost:8080/user" class="btn btn-default">Узнать больше</a>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4><i class="fa fa-fw fa-gift"></i> Java1</h4>
                </div>
                <div class="panel-body">
                    <p>Если Вы прошли курс Java1, то сделали первый небольшой шаг для человека. Но это большой шаг для всего человечества!</p>
                    <a href="http://localhost:8080/user" class="btn btn-default">Узнать больше</a>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4><i class="fa fa-fw fa-compass"></i> Java2</h4>
                </div>
                <div class="panel-body">
                    <p>Закончил курс Java2, значит ты наполовину профессионал. Но умный наполовину значит наполовину глупец. Пройди курс Java WEB и выделись из толпы!</p>
                    <a href="http://localhost:8080/user" class="btn btn-default">Узнать больше</a>
                </div>
            </div>
        </div>
    </div>
    <!-- /.row -->

    <!-- Portfolio Section -->
    <div class="row">
        <div class="col-lg-12">
            <h2 class="page-header">Счастливые обладатели сертификата Java программиста</h2>
        </div>
        <div class="col-md-4 col-sm-6">
            <a href="portfolio-item.html">
                <img class="img-responsive img-portfolio img-hover" src="https://lentachel.ru/netcat_files/Image/foto/2018/01/26/0b19a7c23e1beff25542511f41389921/nunn.jpg" alt="">
            </a>
        </div>
        <div class="col-md-4 col-sm-6">
            <a href="portfolio-item.html">
                <img class="img-responsive img-portfolio img-hover" src="http://lingvo-pro.ru/wp-content/uploads/2015/10/lingvo-pro7.jpg" alt="">
            </a>
        </div>
        <div class="col-md-4 col-sm-6">
            <a href="portfolio-item.html">
                <img class="img-responsive img-portfolio img-hover" src="https://deti.mail.ru/pic/photolib/2014/08/31/Depositphotos_8705621_s.jpg" alt="">
            </a>
        </div>
        <%--<div class="col-md-4 col-sm-6">
            <a href="portfolio-item.html">
                <img class="img-responsive img-portfolio img-hover" src="http://placehold.it/700x450" alt="">
            </a>
        </div>--%>
        <%--<div class="col-md-4 col-sm-6">
            <a href="portfolio-item.html">
                <img class="img-responsive img-portfolio img-hover" src="http://placehold.it/700x450" alt="">
            </a>
        </div>--%>
        <%--<div class="col-md-4 col-sm-6">
            <a href="portfolio-item.html">
                <img class="img-responsive img-portfolio img-hover" src="http://placehold.it/700x450" alt="">
            </a>
        </div>--%>
    </div>
    <!-- /.row -->

    <!-- Features Section -->
    <div class="row">
        <div class="col-lg-12">
            <h2 class="page-header">График прохождения курса</h2>
        </div>
        <div class="col-md-6">
            <p>Курс включает в себя:</p>
            <ul>
                <li><strong>Java Core</strong>
                </li>
                <li>Переменные и функции</li>
                <li>Интерфейсы и классы</li>
                <li>Регулярные выражения</li>
                <li>Дженерики</li>
                <li>Лямбда выражения</li>
            </ul>
            <p>Прохождение курса идет по плану!</p>
        </div>
        <div class="col-md-6">
            <img class="img-responsive" src="https://static3.depositphotos.com/1007836/260/i/950/depositphotos_2608892-stock-photo-business-finance-chart-graph-diagram.jpg" alt="">
        </div>
    </div>
    <!-- /.row -->

    <hr>

    <!-- Call to Action Section -->
    <%--<div class="well">
        <div class="row">
            <div class="col-md-8">
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Molestias, expedita, saepe, vero rerum deleniti beatae veniam harum neque nemo praesentium cum alias asperiores commodi.</p>
            </div>
            <div class="col-md-4">
                <a class="btn btn-lg btn-default btn-block" href="#">Call to Action</a>
            </div>
        </div>
    </div>
--%>
    <hr>

    <!-- Footer -->
    <footer>
        <div class="row">
            <div class="col-lg-12">
                <p>Java Web © JRepetitor 2018</p>
            </div>
        </div>
    </footer>

</div>
<!-- /.container -->

<!-- jQuery -->
<script src="/resources/js/jquery.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="/resources/js/bootstrap.min.js"></script>

<!-- Script to Activate the Carousel -->
<script>
    $('.carousel').carousel({
        interval: 5000 //changes the speed
    })
</script>




</body>
</html>