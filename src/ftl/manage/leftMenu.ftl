<nav class="col-md-2 d-none d-md-block bg-light sidebar">
  <div class="sidebar-sticky">
    <ul class="nav flex-column">
      <li class="nav-item ${('indexPage'==methodName)?string('bg-dark','')}">
        <a class="nav-link ${('indexPage'==methodName)?string('text-light','')}" href="${indexpath}/manage/indexPage.action">
         	 首页
        </a>
      </li>
      <li class="nav-item ${('systemListPage'==methodName)?string('bg-dark','')}">
        <a class="nav-link ${('systemListPage'==methodName)?string('text-light','')}" href="${indexpath}/manage/systemListPage.action">
          	系统页列表
        </a>
      </li>
      <li class="nav-item ${('courseListPage'==methodName)?string('bg-dark','')}">
        <a class="nav-link ${('courseListPage'==methodName)?string('text-light','')}" href="${indexpath}/manage/courseListPage.action">
          	课程列表
        </a>
      </li>
      <li class="nav-item ${('userListPage'==methodName)?string('bg-dark','')}">
        <a class="nav-link ${('userListPage'==methodName)?string('text-light','')}" href="${indexpath}/manage/userListPage.action">
          	用户列表
        </a>
      </li>
      <#if 'allocationRolePage'==methodName>
      	<li class="nav-item ${('allocationRolePage'==methodName)?string('bg-dark','')}">
          <a class="nav-link ${('allocationRolePage'==methodName)?string('text-light','')}" href="${indexpath}/manage/allocationRolePage.action">
          	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          	分配角色
          </a>
        </li>
	  </#if>
      
      <li class="nav-item ${('teacherListPage'==methodName)?string('bg-dark','')}">
        <a class="nav-link ${('teacherListPage'==methodName)?string('text-light','')}" href="${indexpath}/manage/teacherListPage.action">
          	老师列表
        </a>
      </li>
    </ul>
  </div>
</nav>