package Com;

public class ViewsMode {
	public static class Delete{}
	public static class OnlyView extends Delete{};
	public static class Create extends OnlyView{};
}
