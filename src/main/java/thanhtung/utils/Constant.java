package thanhtung.utils;

public class Constant {

    // ========================================================================
    // 🔐 Authentication Constants
    // ========================================================================
    
    /** Tên cookie "Nhớ tôi" trong LoginController */
    public static final String COOKIE_REMEMBER = "username";
    
    /** Session attribute lưu thông tin user */
    public static final String SESSION_ACCOUNT = "account";
    
    /** Thời gian sống cookie (24 giờ) */
    public static final int COOKIE_EXPIRE_TIME = 24 * 60 * 60;

    // ========================================================================
    // 📁 File Upload Constants (PDF 11_UploadFile_servlet)
    // ========================================================================
    
    /** Thư mục lưu file upload */
    public static final String UPLOAD_DIRECTORY = "uploads";
    
    /** Tên file mặc định */
    public static final String DEFAULT_FILENAME = "default.file";
    
    /** Giới hạn kích thước file (5MB) */
    public static final long MAX_FILE_SIZE = 5 * 1024 * 1024L;
    
    /** Định dạng file được phép */
    public static final String[] ALLOWED_EXTENSIONS = {".jpg", ".jpeg", ".png", ".gif"};

    // ========================================================================
    // 📄 JSP View Paths
    // ========================================================================
    
    /** Đường dẫn trang đăng nhập */
    public static final String LOGIN_PATH = "/views/login.jsp";
    
    /** Đường dẫn trang đăng ký */
    public static final String REGISTER_PATH = "/views/register.jsp";
    
    /** Đường dẫn trang chủ */
    public static final String HOME_PATH = "/views/Home.jsp";
    
    /** Đường dẫn admin home */
    public static final String ADMIN_HOME_PATH = "/views/admin/Home.jsp";
    
    /** Đường dẫn danh sách category */
    public static final String CATEGORY_LIST_PATH = "/views/category/list.jsp";
    
    /** Đường dẫn form thêm category */
    public static final String CATEGORY_ADD_PATH = "/views/category/add.jsp";
    
    /** Đường dẫn form sửa category */
    public static final String CATEGORY_EDIT_PATH = "/views/category/edit.jsp";

    // ========================================================================
    // 🎯 Role & Status Constants
    // ========================================================================
    
    /** Role ID Admin */
    public static final int ROLE_ADMIN = 1;
    
    /** Role ID Manager */
    public static final int ROLE_MANAGER = 2;
    
    /** Role ID User */
    public static final int ROLE_USER = 3;

    // ========================================================================
    // 📧 Message Constants
    // ========================================================================
    
    /** Thông báo đăng ký thành công */
    public static final String MSG_REGISTER_SUCCESS = "Đăng ký thành công! Vui lòng đăng nhập.";
    
    /** Thông báo đăng nhập thất bại */
    public static final String MSG_LOGIN_FAILED = "Tài khoản hoặc mật khẩu không đúng!";
    
    /** Thông báo thêm category thành công */
    public static final String MSG_ADD_CATEGORY_SUCCESS = "Thêm category thành công!";
    
    /** Thông báo cập nhật thất bại */
    public static final String MSG_UPDATE_FAILED = "Cập nhật thất bại!";
    
    /** Thông báo lỗi upload */
    public static final String MSG_UPLOAD_ERROR = "Lỗi upload file!";

    // ========================================================================
    // 🗄️ Database Constants
    // ========================================================================
    
    /** Tên database */
    public static final String DB_NAME = "LTWEBTUXA";
    
    /** Tên server SQL */
    public static final String DB_SERVER = "ADMIN-PC\\THANHTUNG";
    
    /** Windows Authentication connection */
    public static final String DB_CONNECTION_W = "jdbc:sqlserver://" + DB_SERVER + 
            ";integratedSecurity=true;databaseName=" + DB_NAME;
}