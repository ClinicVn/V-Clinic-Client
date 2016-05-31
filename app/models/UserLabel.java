package models;

public class UserLabel implements ILabel{
	
	public String Username = "";
	public String Password = "";
	public String RetypePassword = "";
	public String FullName = "";
	public String Address = "";
	public String Email = "";
	public String PhoneNumber = "";
	public String Type = "";
	public String Submmit = "";
	private int language = 1;
	
	public UserLabel(int language){
		this.language = language;
		this.SetLabel();
	}
	@Override
	public void SetLabel() {
		switch(this.language){
			case 1:
				this.Username = "Username";
				this.Password = "Password";
				this.RetypePassword = "Retype password";
				this.FullName = "Full name";
				this.Address = "Address";
				this.Email = "Email";
				this.PhoneNumber = "Phone number";
				this.Type = "Type";
				this.Submmit = "Sing up";
				break;
			case 2:
				this.Username = "Tên đăng nhập";
				this.Password = "Mật khẩu";
				this.RetypePassword = "Gõ lại mật khẩu";
				this.FullName = "Tên đầy đủ";
				this.Address = "Địa chỉ";
				this.Email = "Email";
				this.PhoneNumber = "Số điện thoại";
				this.Type = "Kiểu";
				this.Submmit = "Đăng ký";
				break;
			default:
				break;
		}
	}
	
}