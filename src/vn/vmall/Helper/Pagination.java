package vn.vmall.Helper;

public class Pagination {
	private int current_page=1;// Trang hiện tại
	private  int total_record=1;// Tổng số record
	private int total_page;// Tổng số trang
	private int limit=10;// limit
	private int start=0;// start
	private String link_full="";// Link full có dạng như sau: domain/com/page/{page}
	private String link_first="";// Link trang đầu tiên
	private int range = 9; // Số button trang bạn muốn hiển thị 
	private int min   = 0; // Tham số min
	private int max   = 0;  // tham số max, min và max là 2 tham số private
	
	
	
	
	
	
	
	public int getCurrent_page() {
		return current_page;
	}
	public void setCurrent_page(int current_page) {
		this.current_page = current_page;
	}
	public int getTotal_record() {
		return total_record;
	}
	public void setTotal_record(int total_record) {
		this.total_record = total_record;
	}
	public int getTotal_page() {
		return total_page;
	}
	public void setTotal_page(int total_page) {
		this.total_page = total_page;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public String getLink_full() {
		return link_full;
	}
	public void setLink_full(String link_full) {
		this.link_full = link_full;
	}
	public String getLink_first() {
		return link_first;
	}
	public void setLink_first(String link_first) {
		this.link_first = link_first;
	}
	public int getRange() {
		return range;
	}
	public void setRange(int range) {
		this.range = range;
	}
	public int getMin() {
		return min;
	}
	public void setMin(int min) {
		this.min = min;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	public Pagination( int curent_page,int total_record,int limit,String link_full,String link_first){
		this.current_page=curent_page;
		this.total_record=total_record;
		this.limit=limit;
		this.link_full=link_full;
		this.link_first=link_first;
		//this.range=rage;
		/*
         * Kiểm tra thông số limit truyền vào có nhỏ hơn 0 hay không?
         * Nếu nhỏ hơn thì gán cho limit = 0, vì trong mysql không cho limit bé hơn 0
         */
        if (this.limit < 0){
        	this.limit = 0;
        }
        /*System.out.println("this.total_record:"+ this.total_record);
        System.out.println("this.limit:"+ this.limit);*/
        double  a_tmp=(double)this.total_record / (double)this.limit;
      /*  System.out.println(" double  a:"+a_tmp);
        System.out.println(" celi  a:"+Math.ceil(a_tmp));*/
        //System.out.println(" Math.ceil:"+ Math.ceil(this.total_record / this.limit));
        this.total_page = (int) Math.ceil(a_tmp);
        //System.out.println(" this.total_page:"+ this.total_page);
        /*
         * Sau khi có tổng số trang ta kiểm tra xem nó có nhỏ hơn 0 hay không
         * nếu nhỏ hơn 0 thì gán nó băng 1 ngay. Vì mặc định tổng số trang luôn bằng 1
         */
        if (this.total_page < 0){
        	this.total_page = 1;
        }
        /*
         * Trang hiện tại sẽ rơi vào một trong các trường hợp sau:
         *  - Nếu người dùng truyền vào số trang nhỏ hơn 1 thì ta sẽ gán nó = 1 
         *  - Nếu trang hiện tại người dùng truyền vào lớn hơn tổng số trang
         *    thì ta gán nó bằng tổng số trang
         * Đây là vấn đề giúp web chạy trơn tru hơn, vì đôi khi người dùng cố ý
         * thay đổi tham số trên url nhằm kiểm tra lỗi web của chúng ta
         */
        if (this.current_page < 1){
        	this.current_page = 1;
        	 //  System.out.println(this.current_page+" _1");
        }
         
        if (this.current_page > this.total_page){
        	this.current_page = this.total_page;
            //System.out.println(this.current_page+" _2");
        }
         
       //  System.out.println(this.current_page);
        /* 
         * Tính start, Như bạn biết trong mysql truy vấn sẽ có limit và start
         * Muốn tính start ta phải dựa vào số trang hiện tại và số limit trên mỗi trang
         * và áp dụng công tức start = (current_page - 1)*limit
        */
        this.start = (this.current_page - 1) * this.limit;
         
        /* 
         * Bây giờ ta tính số trang ta show ra trang web
         * Như bạn biết với những website có data lớn thì số trang có thể
         * lên tới hàng trăm trang, chẵng nhẽ ta show hết cả 100 trang?
         * Nên trong bài này tôi hướng dẫn bạn show trong một khoảng nào đó (range)
         * giống website freetuts.net vậy
        */
         
        // Trước tiên tính middle, đây chính là số nằm giữa trong khoảng tổng số trang
        // mà bạn muốn hiển thị ra màn hình
        int middle = (int) Math.ceil(this.range / 2);
 
        // Ta sẽ lâm vào các trường hợp như bên dưới
        // Trong trường hợp này thì nếu tổng số trang mà bé hơn range
        // thì ta show hết luôn, không cần tính toán làm gì
        // tức là gán min = 1 và max = tổng số trang luôn
        if (this.total_page <this.range){
        	this.min = 1;
        	this.max = this.total_page;
        }
        // Trường hợp tổng số trang mà lớn hơn range
        else
        {
            // Ta sẽ gán min = current_page - (middle + 1)
        	this.min =this.current_page - middle + 1;
             
            // Ta sẽ gán max = current_page + (middle - 1)
            this.max = this.current_page + middle - 1;
             
            // Sau khi tính min và max ta sẽ kiểm tra
            // nếu min < 1 thì ta sẽ gán min = 1  và max bằng luôn range
            if (this.min < 1){
                this.min = 1;
                this.max = this.range;
            }
             
            // Ngược lại nếu min > tổng số trang
            // ta gán max = tổng số trang và min = (tổng số trang - range) + 1 
            else if (this.max > this.total_page) 
            {
                this.max = this.total_page;
                this.min = this.total_page - this.range + 1;
            }
        }
	}//end init
	  /*
     * Hàm lấy link theo trang
     */
    public String get_link(int page)
    {
        // Nếu trang < 1 thì ta sẽ lấy link first
        if (page <= 1 && !this.link_first.isEmpty()){
            return this.link_first;
        }
        // Ngược lại ta lấy link_full
        // Như tôi comment ở trên, link full có dạng domain.com/page/{page}.
        // Trong đó {page} là nơi bạn muốn số trang sẽ thay thế vào
        return this.link_full.replace("replace_numberpage", String.valueOf(page));
    }
    /*
     * Hàm lấy mã html
     * Hàm này ban tạo giống theo giao diện của bạn
     * tôi không có config nhiều vì rất rối
     * Bạn thay đổi theo giao diện của bạn nhé
     */
   public String html()
    {   
        String p = "";
        if (this.total_record > this.limit)
        {
            p = "<ul class='pagination pagination-lg'>";
             
            // Nút prev và first
            if (this.current_page > 1)
            {
            	 p= p.concat( "<li><a href='"+this.get_link(1)+"' >首页</a></li>");
            	 p= p.concat("<li><a href='"+this.get_link(this.current_page-1)+"' aria-label='Previous'>"
                		+ "<span aria-hidden='true'>&laquo;</span></a></li>");
            }
            
            // lặp trong khoảng cách giữa min và max để hiển thị các nút
            for (int i = this.min; i <= this.max; i++)
            {
                // Trang hiện tại
                if (this.current_page == i){
                	 p= p.concat ("<li class='active'><span>"+i+"</span></li>");
                }
                else{
                	 p= p.concat ("<li><a href='"+this.get_link(i)+"'>"+i+"</a></li>");
                }
            }
 
            // Nút last và next
            if (this.current_page < this.total_page)
            {
            	 p= p.concat ("<li><a href='"+this.get_link(this.current_page + 1)+"'  aria-label='Next'> "
            	 		+ "<span aria-hidden='true'>&raquo;</span></a></li>");
            	 p= p.concat ("<li><a href='"+this.get_link(this.total_page)+"'>尾页</a></li>");
            }
             
            p= p.concat( "</ul>");
        }
        return p;
    }//end html()
@Override
public String toString() {
	return "Pagination [current_page=" + current_page + ", total_record="
			+ total_record + ", total_page=" + total_page + ", limit=" + limit
			+ ", start=" + start + ", link_full=" + link_full + ", link_first="
			+ link_first + ", range=" + range + ", min=" + min + ", max=" + max
			+ "]";
}
   
   
}//end class
    

