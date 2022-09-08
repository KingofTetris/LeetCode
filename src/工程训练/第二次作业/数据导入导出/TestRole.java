package 工程训练.第二次作业.数据导入导出;

import java.util.Objects;

public class TestRole {
	private String id;
	private String role_name;
	private String role_code;
	private String description;
	private String create_by;
	private String create_time;
	private String update_by;
	private String update_time;
	public TestRole(){
		
	}
	public TestRole(String id, String role_name, String role_code, String description, String create_by, String create_time, String update_by, String update_time) {
		this.id = id;
		this.role_name = role_name;
		this.role_code = role_code;
		this.description = description;
		this.create_by = create_by;
		this.create_time = create_time;
		this.update_by = update_by;
		this.update_time = update_time;
	}

	public void setId(String id) {
		this.id=id;
	}
	public String getId() {
		return id;
	}
	public void setRoleName(String role_name) {
		this.role_name=role_name;
	}
	public String getRoleName() {
		return role_name;
	}
	public void setRoleCode(String role_code) {
		this.role_code=role_code;
	}
	public String getRoleCode() {
		return role_code;
	}
	public void setDescription(String description) {
		this.description=description;
	}
	public String getDescription() {
		return description;
	}
	public void setCreateBy(String create_by) {
		this.create_by=create_by;
	}
	public String getCreateBy() {
		return create_by;
	}
	public void setCreateTime(String create_time) {
		this.create_time=create_time;
	}
	public String getCreateTime() {
		return create_time;
	}
	public void setUpdateBy(String update_by) {
		this.update_by=update_by;
	}
	public String getUpdateBy() {
		return update_by;
	}
	public void setUpdateTime(String update_time) {
		this.update_time=update_time;
	}
	public String getUpdateTime() {
		return update_time;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		TestRole testRole = (TestRole) o;
		return Objects.equals(id, testRole.id) && Objects.equals(role_name, testRole.role_name) && Objects.equals(role_code, testRole.role_code) && Objects.equals(description, testRole.description) && Objects.equals(create_by, testRole.create_by) && Objects.equals(create_time, testRole.create_time) && Objects.equals(update_by, testRole.update_by) && Objects.equals(update_time, testRole.update_time);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, role_name, role_code, description, create_by, create_time, update_by, update_time);
	}
}

