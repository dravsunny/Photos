package View;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import App.Photos;
import View.Helper.Album;
import View.Helper.Photo;
import View.Helper.User;
import View.Utility.readwriteObjects;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
/**
 * 
 * @author Jaideep Duggempudi
 * @author Suneet Dravida
 * 
 */
public class UserController {
	
	@FXML
	TreeView<String> treeview;
	@FXML
	ImageView imageview;
	@FXML
	Label albumname;
	@FXML
	Label range;
	@FXML
	Label numberphotos;
	@FXML
	TextField newAlbum;
	@FXML
	Label caption;
	@FXML
	Label date;
	@FXML
	Label tags;
	@FXML
	ChoiceBox<String> tagbox;
	@FXML 
	TextField tagvalue;
	@FXML
	TextField newTag;
	@FXML
	Label attribute;
	@FXML
	TabPane tabpane;
	@FXML
	ChoiceBox<String> salbum;
	@FXML
	ChoiceBox<String> dalbum;
	@FXML
	ChoiceBox<String> sphoto;
	@FXML
	ChoiceBox<String> salbum1;
	@FXML
	ChoiceBox<String> dalbum1;
	@FXML
	ChoiceBox<String> sphoto1;
	@FXML
	Label photodelete;
	@FXML
	Label albumdelete;
	@FXML
	Label cp;
	@FXML
	Label mp;
	@FXML
	TextField recaption;
	@FXML
	TextField rename;
	@FXML
	ChoiceBox<String> sa;
	@FXML
	ChoiceBox<String> da;
	@FXML
	ChoiceBox<String> sp;
	@FXML
	DatePicker start;
	@FXML
	ChoiceBox<String> ta;
	@FXML
	ChoiceBox<String> tp;
	@FXML
	ChoiceBox<String> tt;
	@FXML
	ChoiceBox<String> tagd;
	@FXML 
	ScrollPane scrollpane;
	@FXML
	ListView<String> listview;
	@FXML
	ChoiceBox<String> first;
	@FXML
	ChoiceBox<String> second;
	@FXML
	ChoiceBox<String> andor;
	@FXML
	DatePicker end;
	@FXML
	TextField firsts;
	@FXML
	TextField seconds;
	@FXML
	TextField searchalbum;
	
	/**
	 * @param user stores the current user
	 */
	public User user;
	/**
	 * @param stage maintains the stage
	 */
	public Stage stage;
	/**
	 * @param root stores the root of the treeview
	 */
	public TreeItem<String> root;
	/**
	 * @param stock stores the stock album
	 */
	public Album stock;
	/**
	 * @param tilpane keeps track of the tilepane
	 */
	public TilePane tilepane;
	
	/**
	 * The class initiate starts the the User GUI for the user. It keeps track of several listeners such as for the listviews, combo
	 * box, and treeviews and image view and set view; It is the main controller class for the user and it helps enact several transaction
	 * such as populating the treeview and combo boxes
	 * 
	 * 
	 * @param stage stores the stage
	 * @param user stores the user 
	 * @throws IOException
	 */
	public void initiate(Stage stage, User user) throws IOException
	{
		this.stage = stage;
		this.user = user;
		
		tabpane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		tilepane = new TilePane();
		
		root = new TreeItem<String>("Photo Library");
		
		File stockFolder = new File("data");
		
	
		
		stock = new Album("Stock Images");
		TreeItem<String> s = new TreeItem<String>(stock.getalbumName());
		
		for(File file: stockFolder.listFiles())
		{
			String filepath = file.getCanonicalPath();
			String caption = file.getName().substring(0, file.getName().indexOf("."));
			
			Date date = null;
	        Calendar calendar = Calendar.getInstance();
	        calendar.set(Calendar.MILLISECOND, 0);
	        calendar.setTimeInMillis(file.lastModified());
	        date = calendar.getTime();
	        //Date datde = Date.from(start.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
	        //System.out.println(datde.compareTo(date));
	        Photo photo = new Photo(filepath, caption, date);
	        stock.addPhoto(photo);
	        
	        TreeItem<String> p = new TreeItem<String>(photo.getCaption());
	        s.getChildren().add(p);
	        
		}
		if(!user.getlistofAlbums().isEmpty())
		{
			for(String i: user.getlistofAlbums().keySet())
			{
				TreeItem<String> m = new TreeItem<String>(i);
				if(!user.getlistofAlbums().get(i).getlistofPhotos().isEmpty())
				{
					for(String l: user.getAlbum(i).getlistofPhotos().keySet())
					{
						TreeItem<String> t = new TreeItem<String>(l);
						m.getChildren().add(t);
					}
				}
				root.getChildren().add(m);
			}
		}
		
		root.getChildren().add(s);
		treeview.setRoot(root);
		
		

		
		tabpane.getSelectionModel().selectedIndexProperty().addListener((obs, oldVal, newVal) -> {
			
			listview.getItems().clear();
			treeview.getSelectionModel().select(treeview.getRoot());
			imageview.setImage(null);
			andor.getItems().clear();
			andor.getItems().addAll("and", "or", "one Tag search");
			first.getItems().clear();
			first.getItems().addAll(user.getlistofAttributes());
			second.getItems().clear();
			second.getItems().addAll(user.getlistofAttributes());
			
			salbum.getItems().clear();
			dalbum.getItems().clear();
			salbum1.getItems().clear();
			dalbum1.getItems().clear();
			sa.getItems().clear();
			da.getItems().clear();
			ta.getItems().clear();
			tp.getItems().clear();
			tt.getItems().clear();
			tagd.getItems().clear();
			
			
			if(user.getlistofAlbums().keySet().size() >= 2)
			{
				ObservableSet<String>obsList = FXCollections.observableSet(user.getlistofAlbums().keySet());
				salbum.getItems().addAll(obsList);
				dalbum.getItems().addAll(obsList);
				
				salbum.getSelectionModel().selectedItemProperty().addListener((ob, oldVa, newVa) -> {
					
					cp.setVisible(false);
					mp.setVisible(false);
					sphoto.getItems().clear();
					
					if(!(user.getlistofAlbums().get(salbum.getValue()) == null))
					{
						ObservableSet<String>obsSet = 
								FXCollections.observableSet(user.getlistofAlbums().get(salbum.getValue()).getlistofPhotos().keySet());
						sphoto.getItems().addAll(obsSet);
						
					}
					
					
					
				});
				
				
				
			}
			
			ObservableSet<String>obList = FXCollections.observableSet(user.getlistofAlbums().keySet());
			salbum1.getItems().addAll(obList);
			dalbum1.getItems().addAll(obList);
			
			salbum1.getSelectionModel().selectedItemProperty().addListener((ob, oldVa, newVa) -> {
				
				photodelete.setVisible(false);
			
				sphoto1.getItems().clear();
				
				if(!(user.getlistofAlbums().get(salbum1.getValue()) == null))
				{
					ObservableSet<String>obsSet = 
							FXCollections.observableSet(user.getlistofAlbums().get(salbum1.getValue()).getlistofPhotos().keySet());
					sphoto1.getItems().addAll(obsSet);
					
				}
			
			
			});
			dalbum1.getSelectionModel().selectedItemProperty().addListener((ob, oldVa, newVa) -> {
			
				albumdelete.setVisible(false);
				
			});
			
			//rename recaption
			ObservableSet<String>oList = FXCollections.observableSet(user.getlistofAlbums().keySet());
			sa.getItems().addAll(oList);
			da.getItems().addAll(oList);
			
			sa.getSelectionModel().selectedItemProperty().addListener((ob, oldVa, newVa) -> {
				
				
			
				sp.getItems().clear();
				
				if(!(user.getlistofAlbums().get(sa.getValue()) == null))
				{
					ObservableSet<String>obsSet = 
							FXCollections.observableSet(user.getlistofAlbums().get(sa.getValue()).getlistofPhotos().keySet());
					sp.getItems().addAll(obsSet);
					
				}
			
			
			});
			da.getSelectionModel().selectedItemProperty().addListener((ob, oldVa, newVa) -> {
			
				//albumdelete.setVisible(false);
				
			});
			//delete tag
			ObservableSet<String>sList = FXCollections.observableSet(user.getlistofAlbums().keySet());
			ta.getItems().addAll(sList);
		
			
			ta.getSelectionModel().selectedItemProperty().addListener((ob, oldVa, newVa) -> {
				
				tp.getItems().clear();
				System.out.println(user.getlistofAlbums().get(ta.getValue()).getlistofPhotos().toString());
				if(!(user.getlistofAlbums().get(ta.getValue()).getlistofPhotos() == null))
				{
					ObservableSet<String>objjsSet = 
							FXCollections.observableSet(user.getlistofAlbums().get(ta.getValue()).getlistofPhotos().keySet());
					tp.getItems().addAll(objjsSet);
						
					tp.getSelectionModel().selectedItemProperty().addListener((o, oldV, newV) -> {
						
						tt.getItems().clear();
						
						if(((user.getlistofAlbums().get(ta.getValue()).getlistofPhotos()).get(tp.getValue()).getlistofTags()) != null)
						{
							System.out.println(user.getlistofAlbums().get(ta.getValue()).getlistofPhotos().get(tp.getValue()));
								ObservableSet<String>obsSmme = 
									FXCollections.observableSet(user.getlistofAlbums().get(ta.getValue()).getlistofPhotos().get(tp.getValue()).getlistofTags().keySet());
									tt.getItems().addAll(obsSmme);
									
									tt.getSelectionModel().selectedItemProperty().addListener((od, olVajkj, nwV) -> {
										System.out.println(user.getlistofAlbums().get(ta.getValue()).getlistofPhotos().get(tp.getValue()).getlistofTags().toString());
									
										
											tagd.getItems().clear();
									
										
											ObservableSet<String>obffsS = 
													FXCollections.observableSet(user.getlistofAlbums().get(ta.getValue()).getlistofPhotos().get(tp.getValue()).gettagValues(tt.getValue()));
													tagd.getItems().addAll(obffsS);
										
									
									});
							
						}
					});
					
					
				}
			
			
			});
		
			
			
		});
		
		
		treeview
		.getSelectionModel()
		.selectedIndexProperty()
		.addListener(
				(obs, oldVal, newVal) -> {
				//showItem(mainStage)
				
				tilepane.getChildren().clear();
				
				TreeItem<String> item = treeview.getSelectionModel().getSelectedItem();
				if(item.getValue().equals("Photo Library"));
				{
					caption.setText("\n");
					 date.setText("\n");
					 tags.setText("\n");
					 albumname.setText("\n");
			
					 range.setText("\n");
					 numberphotos.setText("\n");
				}

				if(item.getChildren().isEmpty() && !item.getParent().getValue().equals("Photo Library"))
				{
					try {
						displayImage(item);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(!item.getValue().equals("Photo Library") && item.getParent().getValue().equals("Photo Library"))
				{
					try {
						 imageview.setImage(null);
						 caption.setText("\n");
						 date.setText("\n");
						 tags.setText("\n");
						 displayThumbnail(item);
						
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				
			});
		
				if(user.getlistofAttributes().isEmpty())
				{
					user.getlistofAttributes().add("Location");
					user.getlistofAttributes().add("People");
				}
		
				ObservableList<String>obsList = FXCollections.observableArrayList(user.getlistofAttributes());
				tagbox.getItems().addAll(obsList);
				tagbox.getSelectionModel().select(0);
			
		
		
		
		stage.setOnCloseRequest(event -> {
			try {
				readwriteObjects.writeUser(user);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		
	}
	/**
	 * The displayImage class shows the image that was selected on the treeview. It also calls the display thumnbnail
	 * 
	 * @param photo delineated the photo picked in the tree view
	 * @throws FileNotFoundException
	 */
	public void displayImage(TreeItem<String> photo) throws FileNotFoundException
	{
		
		if(photo.getParent().getValue().equals("Stock Images"))
		{
			InputStream stream = new FileInputStream(stock.getPhoto(photo.getValue()).getFilepath());
		    Image image = new Image(stream);
		    imageview.setImage(image);
		    
		    
		    caption.setText(stock.getPhoto(photo.getValue()).getCaption());
		    date.setText(stock.getPhoto(photo.getValue()).getstringDate());
		    stock.getPhoto(photo.getValue()).stringofTags();
		    tags.setText("\n");

		}
		else
		{
			InputStream stream = new FileInputStream(user.getAlbum(photo.getParent().getValue()).getPhoto(photo.getValue()).getFilepath());
		    Image image = new Image(stream);
		    imageview.setImage(image);
		    caption.setText(user.getAlbum(photo.getParent().getValue()).getPhoto(photo.getValue()).getCaption());
		    date.setText(user.getAlbum(photo.getParent().getValue()).getPhoto(photo.getValue()).getstringDate());
		    tags.setText(user.getAlbum(photo.getParent().getValue()).getPhoto(photo.getValue()).stringofTags());
		   
		}
		imageview.setFitHeight(225);
	    imageview.setFitWidth(475);
	    imageview.setPreserveRatio(false);
		displayThumbnail(photo.getParent());
		
			
		
		
	}
	/**
	 * The displayThumnail mthod essentially is just used to display the thumnail images of the album chosen
	 * 
	 * @param album delineateds the album chosen in the treeview
	 * @throws FileNotFoundException
	 */
	public void displayThumbnail(TreeItem<String> album) throws FileNotFoundException
	{
		
		Album temp = new Album();
		if(album.getValue().equals("Stock Images"))
		{
			temp = stock;
		}
		else
		{
			temp = user.getAlbum(album.getValue());
		}
		
		albumname.setText(temp.getalbumName());
		range.setText(temp.getRangeofDates());
		numberphotos.setText(String.valueOf(temp.getnumberofPhotos()));
		
		for(TreeItem<String> i : album.getChildren())
		{
			InputStream stream = new FileInputStream(temp.getPhoto(i.getValue()).getFilepath());
			Image image = new Image(stream, 50, 30, false, false);
			ImageView iv = new ImageView(image);
			iv.setPreserveRatio(true);
		    iv.setSmooth(true);
		    GridPane g = new GridPane();

		    g.add(iv, 0, 0);
		    Label a = new Label(temp.getPhoto(i.getValue()).getCaption());
		    g.add(a, 0, 1);
		 
			tilepane.getChildren().add(g);
			
			tilepane.setHgap(1);
			
			scrollpane.setContent(tilepane);
			scrollpane.setPannable(true);
			
		}
		
		
	}
	/**
	 * The addAlbum method adds the album to the users list of albums and add it to the tree view.
	 * 
	 * @param e buttonpressed for addding album
	 */
	public void addAlbum(ActionEvent e)
	{
		if(newAlbum.getText().equals(""))
		{
			Alert alerts = new Alert(AlertType.INFORMATION);
			alerts.initOwner(stage);
			alerts.setTitle("Error");
			alerts.setHeaderText("There is an Error");
			alerts.setContentText("You must put in the Album Name to Proceed");
			alerts.showAndWait();
		
		}
		else if(user.getlistofAlbums().containsKey(newAlbum.getText()))
		{
			Alert alerts = new Alert(AlertType.INFORMATION);
			alerts.initOwner(stage);
			alerts.setTitle("Error");
			alerts.setHeaderText("There is an Error");
			alerts.setContentText("An album already exists with this Name");
			alerts.showAndWait();
		}
		else
		{
			Album newalbum = new Album(newAlbum.getText());
			user.addAlbum(newalbum);
			TreeItem<String> a = new TreeItem<String>(newalbum.getalbumName());
			treeview.getRoot().getChildren().add(a);
		}
	}
	/**
	 * addphotos method adds the photos from users system to the gui via it sfilepath and caption
	 * 
	 * @param e
	 * @throws IOException
	 */
	public void addPhotos(ActionEvent e) throws IOException
	{
		if(treeview.getSelectionModel().getSelectedItem().getValue().equals("Stock Images"))
		{
			Alert alerts = new Alert(AlertType.INFORMATION);
			alerts.initOwner(stage);
			alerts.setTitle("Error");
			alerts.setHeaderText("There is an Error");
			alerts.setContentText("You cannot add to the Stock Images Folder");
			alerts.showAndWait();
		
		}
		if(treeview.getSelectionModel().getSelectedItem() != null && treeview.getSelectionModel().getSelectedItem().getParent().getValue().equals("Photo Library") || treeview.getSelectionModel().getSelectedItem().getValue().equals("Photo Library"))
		{
	 
			FileChooser filechooser = new FileChooser();
			
			filechooser.getExtensionFilters().addAll(new ExtensionFilter("Img File", "*.bmp", "*.gif", "*.jpg", "*png"));
			
			File selectedFile = filechooser.showOpenDialog(null);
			
			if(selectedFile != null)
			{
				String filepath = selectedFile.getCanonicalPath();
				String caption = selectedFile.getName().substring(0, selectedFile.getName().indexOf("."));
				if(user.getAlbum(treeview.getSelectionModel().getSelectedItem().getValue()).getlistofPhotos().containsKey(caption))
				{
					Alert alerts = new Alert(AlertType.INFORMATION);
					alerts.initOwner(stage);
					alerts.setTitle("Error");
					alerts.setHeaderText("There is an Error");
					alerts.setContentText("There is an image like this already in your album");
					alerts.showAndWait();
				}
				else
				{
					Date date = null;
			        Calendar calendar = Calendar.getInstance();
			        calendar.set(Calendar.MILLISECOND, 0);
			        calendar.setTimeInMillis(selectedFile.lastModified());
			        date = calendar.getTime();

			        Photo p = new Photo(filepath, caption, date);
			        user.getlistofAlbums().get(treeview.getSelectionModel().getSelectedItem().getValue()).addPhoto(p);
			        TreeItem<String> np = new TreeItem<String>(p.getCaption());  
			        treeview.getSelectionModel().getSelectedItem().getChildren().add(np);
			        treeview.getSelectionModel().select(np);
				}
				
		        
			}
			
		}
		else
		{
			Alert alerts = new Alert(AlertType.INFORMATION);
			alerts.initOwner(stage);
			alerts.setTitle("Error");
			alerts.setHeaderText("There is an Error");
			alerts.setContentText("Select the Album from the TreeView");
			alerts.showAndWait();
		
		}
		
	}
	/**
	 * the addAttribute method allows the user to add a tag type or attribute for which they can
	 * put in tag values for
	 * 
	 * @param e button pressed on gui
	 */
	public void addAttribute(ActionEvent e)
	{
		if(newTag.getText().equals(""))
		{
			Alert alerts = new Alert(AlertType.INFORMATION);
			alerts.initOwner(stage);
			alerts.setTitle("Error");
			alerts.setHeaderText("There is an Error");
			alerts.setContentText("You must put in the Attribute Name to Proceed");
			alerts.showAndWait();
		
		}
		else if(user.getlistofAttributes().contains(newTag.getText()))
		{
			Alert alerts = new Alert(AlertType.INFORMATION);
			alerts.initOwner(stage);
			alerts.setTitle("Error");
			alerts.setHeaderText("There is an Error");
			alerts.setContentText("A tag already exists with this name");
			alerts.showAndWait();
		}
		else
		{
			user.addAttribute(newTag.getText());
			
		}
		tagbox.getItems().clear();
		ObservableList<String>obsList = FXCollections.observableArrayList(user.getlistofAttributes());
		tagbox.getItems().addAll(obsList);
		tagbox.getSelectionModel().select(0);
		attribute.setTextAlignment(TextAlignment.CENTER);
		attribute.setText("Add Another Attribute?");
		attribute.setTextAlignment(TextAlignment.CENTER);
	}
	/**
	 * the add newPhotoTags methods allows you add a tag to a photo by selecting it from the treeview
	 * 
	 * @param e form button pressed
	 */
	public void addnewPhotoTags(ActionEvent e)
	{
		TreeItem<String> si = treeview.getSelectionModel().getSelectedItem();
	
		
		if(tagvalue.getText().equals(""))
		{
			Alert alerts = new Alert(AlertType.INFORMATION);
			alerts.initOwner(stage);
			alerts.setTitle("Adding Tags");
			alerts.setHeaderText("To add Tag to your Photo");
			alerts.setContentText("Please put in a value for the Tag in the TextBox");
			alerts.showAndWait();
		}
		else if(si.getValue().equals(treeview.getRoot().getValue()) || si.getParent().getValue().equals("Photo Library") || si.getParent().getValue().equals("Stock Images"))
		{
			Alert alerts = new Alert(AlertType.INFORMATION);
			alerts.initOwner(stage);
			alerts.setTitle("Adding Tags");
			alerts.setHeaderText("Please Select a Photo, not an Album");
			alerts.setContentText("Select a Photo from the Treeview to add Tag. You can also not add Tags to Stock Images");
			alerts.showAndWait();
		}
		else
		{
			user.getlistofAlbums().get(si.getParent().getValue()).getlistofPhotos().get(si.getValue()).addnewTag(tagbox.getValue(), tagvalue.getText());
			tags.setText(user.getAlbum(si.getParent().getValue()).getPhoto(si.getValue()).stringofTags());
			
		}
		
		
		
	}
	/**
	 * copy method allows a user the copy a photo from the source album to a destination album.
	 * @param e
	 */
	public void copy(ActionEvent e)
	{
		if(!(sphoto.getValue() == null) && !(dalbum.getValue() == null))
		{
			Photo temp = user.getlistofAlbums().get(salbum.getValue()).getPhoto(sphoto.getValue());
			TreeItem<String> treeitem = new TreeItem<String>(temp.getCaption());
			
			for(TreeItem<String> i : treeview.getRoot().getChildren())
			{
				if(i.getValue().equals(dalbum.getValue()))
				{
					i.getChildren().add(treeitem);
					break;
				}
			}
			user.getlistofAlbums().get(dalbum.getValue()).addPhoto(temp);
			cp.setVisible(true);
			
			salbum.getItems().clear();
			dalbum.getItems().clear();
			ObservableSet<String>obsList = FXCollections.observableSet(user.getlistofAlbums().keySet());
			salbum.getItems().addAll(obsList);
			dalbum.getItems().addAll(obsList);
		}
		else
		{
			Alert alerts = new Alert(AlertType.INFORMATION);
			alerts.initOwner(stage);
			alerts.setTitle("Error");
			alerts.setHeaderText("Please Select a Photo");
			alerts.setContentText("It is not possible to move your photo. It might be due to not having more than 1 album or your"
					+ "Source Album does not have any photos. Or that you have not selected a Photo or Destination album.");
			alerts.showAndWait();
		}
	
		
	}
	/**
	 * the move method allows you to move a phot from a source album to a destination album
	 * 
	 * @param e from button pressed 
	 */
	public void move(ActionEvent e)
	{
		
		if(!(sphoto.getValue() == null) && !(dalbum.getValue() == null))
		{
			
			Photo temp = user.getlistofAlbums().get(salbum.getValue()).getPhoto(sphoto.getValue());
			
			TreeItem<String> treeitem = new TreeItem<String>(temp.getCaption());
			user.getlistofAlbums().get(salbum.getValue()).deletePhoto(temp.getCaption());
			
			for(TreeItem<String> i : treeview.getRoot().getChildren())
			{
				if(i.getValue().equals(dalbum.getValue()))
				{
					i.getChildren().add(treeitem);
				}
				if(i.getValue().equals(salbum.getValue()))
				{
					for(TreeItem<String> j : i.getChildren())
					{
						if(j.getValue().equals(sphoto.getValue()))
						{
							i.getChildren().remove(j);
							break;
						}
					}
				}
			}
			user.getlistofAlbums().get(dalbum.getValue()).addPhoto(temp);
			mp.setVisible(true);
			salbum.getItems().clear();
			dalbum.getItems().clear();
			ObservableSet<String>obsList = FXCollections.observableSet(user.getlistofAlbums().keySet());
			salbum.getItems().addAll(obsList);
			dalbum.getItems().addAll(obsList);
		}
		else if(salbum.getValue().equals(dalbum.getValue()))
		{
			Alert alerts = new Alert(AlertType.INFORMATION);
			alerts.initOwner(stage);
			alerts.setTitle("Error");
			alerts.setHeaderText("Please Select a Photo!");
			alerts.setContentText("The Source and Destination Album must be DIFFERENT");
			alerts.showAndWait();
		}
		else
		{
			Alert alerts = new Alert(AlertType.INFORMATION);
			alerts.initOwner(stage);
			alerts.setTitle("Error");
			alerts.setHeaderText("Please Select a Photo!");
			alerts.setContentText("It is not possible to move your photo. It might be due to not having more than 1 album or your "
					+ "Source Album does not have any photos. Or that you have not selected a Photo.");
			alerts.showAndWait();
		}
		
	
		
	}
	/**
	 * the delete photo method allows you to delete photos by selecting it from the treeview
	 * 
	 * @param e button pressed
	 */
	public void deletePhotos(ActionEvent e)
	{
		if(!(sphoto1.getValue() == null))
		{
			user.getlistofAlbums().get(salbum1.getValue()).deletePhoto(sphoto1.getValue());
			
			for(TreeItem<String> i : treeview.getRoot().getChildren())
			{
				if(i.getValue().equals(salbum1.getValue()))
				{
					for(TreeItem<String> j : i.getChildren())
					{
						if(j.getValue().equals(sphoto1.getValue()))
						{
							i.getChildren().remove(j);
							break;
						}
					}
				}
			}
			photodelete.setVisible(true);
			salbum1.getItems().clear();
			ObservableSet<String>obsList = FXCollections.observableSet(user.getlistofAlbums().keySet());
			salbum1.getItems().addAll(obsList);
		}
		else
		{
			Alert alerts = new Alert(AlertType.INFORMATION);
			alerts.initOwner(stage);
			alerts.setTitle("Error");
			alerts.setHeaderText("Please Select a Photo To delete!");
			alerts.setContentText("If you want to delete a Photo, please select the album and the photo you want to delte from the dropdown menus");
			alerts.showAndWait();
		}
		
		
		
		
	}
	/**
	 * the delete albums method deltes an album selected form the treeview
	 * 
	 * @param e from button pressed
	 */
	public void delelteAlbums(ActionEvent e)
	{
		if(!(dalbum1.getValue() == null))
		{
			user.deleteAlbum(dalbum1.getValue());;
			
			TreeItem<String> m = null;
			for(TreeItem<String> i : treeview.getRoot().getChildren())
			{
				if(i.getValue().equals(dalbum1.getValue()))
				{
					m = i;
					break;
				}
			}
			treeview.getRoot().getChildren().remove(m);
			
			albumdelete.setVisible(true);
			salbum1.getItems().clear();
			dalbum1.getItems().clear();
			ObservableSet<String>obsList = FXCollections.observableSet(user.getlistofAlbums().keySet());
			salbum1.getItems().addAll(obsList);
			dalbum1.getItems().addAll(obsList);
		}
		else
		{
			Alert alerts = new Alert(AlertType.INFORMATION);
			alerts.initOwner(stage);
			alerts.setTitle("Error");
			alerts.setHeaderText("Please Select the Album To delete!");
			alerts.setContentText("If you want to delete a Album, please select it from the dropdown menu");
			alerts.showAndWait();
		}
		
		
		
	}
	/**
	 * the renamephoto method allows you to rename a photo
	 * @param e button rpessed
	 */
	public void renamePhoto(ActionEvent e)
	{
		
		if(!(sp.getValue() == null) && !recaption.getText().equals("") && !recaption.getText().equals(sp.getValue()))
		{
			user.getlistofAlbums().get(sa.getValue()).editPhotoCaption(sp.getValue(), recaption.getText());;
			
			for(TreeItem<String> i : treeview.getRoot().getChildren())
			{
				if(i.getValue().equals(sa.getValue()))
				{
					for(TreeItem<String> j : i.getChildren())
					{
						if(j.getValue().equals(sp.getValue()))
						{
							j.setValue(recaption.getText());
							break;
						}
					}
				}
			}
			
			sa.getItems().clear();
			ObservableSet<String>obsList = FXCollections.observableSet(user.getlistofAlbums().keySet());
			sa.getItems().addAll(obsList);
		}
		else
		{
			Alert alerts = new Alert(AlertType.INFORMATION);
			alerts.initOwner(stage);
			alerts.setTitle("Error");
			alerts.setHeaderText("Please Select a Photo Rename!");
			alerts.setContentText("If you want to recaption a Photo, please select a Photo from the dropdown and fill in the TextField with a different Name");
			alerts.showAndWait();
		}
		
	}
	/**
	 * this method allows you to rename a album
	 * 
	 * @param e
	 */
	public void renameAlbum(ActionEvent e)
	{
		
		if(!(da.getValue() == null) && !rename.getText().equals("") && !rename.getText().equals(da.getValue()))
		{
			user.editAlbumName(da.getValue(), rename.getText());
			
			
			for(TreeItem<String> i : treeview.getRoot().getChildren())
			{
				if(i.getValue().equals(da.getValue()))
				{
					i.setValue(rename.getText());
					break;
				}
			}
			
			sa.getItems().clear();
			da.getItems().clear();
			ObservableSet<String>obsList = FXCollections.observableSet(user.getlistofAlbums().keySet());
			sa.getItems().addAll(obsList);
			da.getItems().addAll(obsList);
		}
		else
		{
			Alert alerts = new Alert(AlertType.INFORMATION);
			alerts.initOwner(stage);
			alerts.setTitle("Error");
			alerts.setHeaderText("Please Select the Album To rename!");
			alerts.setContentText("If you want to Rename a Album, please select it from the dropdown menu with its NEW name in the field");
			alerts.showAndWait();
		}
		
	}
	/**
	 * the delte tage method allows you to delete a tag from a photo
	 * @param e from button pressed
	 */
	public void deleteTag(ActionEvent e)
	{
		if(tagd.getValue() != null)
		{
			user.getAlbum(ta.getValue()).getlistofPhotos().get(tp.getValue()).removeTag(tt.getValue(), tagd.getValue());
			tagd.getItems().clear();
			
		}
		else
		{
			Alert alerts = new Alert(AlertType.INFORMATION);
			alerts.initOwner(stage);
			alerts.setTitle("Error");
			alerts.setHeaderText("Please Select the tag To Delete!");
			alerts.setContentText("Please select the tag to delete");
			alerts.showAndWait();
		}
	}
	/**
	 * This method is used to go to the next photo on a slideshow
	 * 
	 * @param e
	 */
	public void goNext(ActionEvent e)
	{
		treeview.getSelectionModel().select(treeview.getSelectionModel().getSelectedIndex()+1);
	}
	/**
	 * this method is used to go to the previipus photo on a slide show
	 * 
	 * @param e from button pressed
	 */
	public void goPrevious(ActionEvent e)
	{
		treeview.getSelectionModel().select(treeview.getSelectionModel().getSelectedIndex()-1);
	}
	/**
	 * this method stores the user data an takes user back to the logout screen
	 * 
	 * @param e
	 * @throws Exception
	 */
	public void logout(ActionEvent e) throws Exception
	{
		readwriteObjects.writeUser(user);
		Photos logou = new Photos();
		logou.start(stage);
	}
	/**
	 * this method is used to search photos by date and add them to a listview where they can be stored in a new album
	 * 
	 * @param e
	 */
	
	public void searchbyDate(ActionEvent e)
	{
		listview.getItems().clear();
		
		
		if(start.getValue() == null || end.getValue() == null)
		{
			Alert alerts = new Alert(AlertType.INFORMATION);
			alerts.initOwner(stage);
			alerts.setTitle("Error");
			alerts.setHeaderText("Date");
			alerts.setContentText("You must put the start and end date of your Query");
			alerts.showAndWait();
		}
		else if(start.getValue().compareTo(end.getValue()) == 1)
		{
			Alert alerts = new Alert(AlertType.INFORMATION);
			alerts.initOwner(stage);
			alerts.setTitle("Error");
			alerts.setHeaderText("Date");
			alerts.setContentText("The end date cannot be before your start date");
			alerts.showAndWait();
		}
		else
		{
			Date startdate = Date.from(start.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
	        Date enddate = Date.from(end.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
	        
	        HashSet<String> list = new HashSet<String>();
	        
	        for(String i : user.getlistofAlbums().keySet())
	        {
	        	
	        	for (String j: user.getlistofAlbums().get(i).getlistofPhotos().keySet())
	        	{
	        		Photo jp = user.getlistofAlbums().get(i).getlistofPhotos().get(j);
	        		
	        		if(jp.getDate().compareTo(startdate) == 1 && jp.getDate().compareTo(enddate) == -1)
	        		{
	        			list.add(jp.getCaption());
	        		}
	        	}
	        }
	        
	        ArrayList<String> tem = new ArrayList<String>();
			tem.addAll(list);
			listview.getItems().addAll(FXCollections.observableArrayList(tem));
	        
		}
	}
	/**
	 * this is used to add the photos from the search queries to a new albun
	 * 
	 * @param e
	 */
	public void addsearchalbum(ActionEvent e)
	{
		if(searchalbum.getText().equals(""))
		{
			Alert alerts = new Alert(AlertType.INFORMATION);
			alerts.initOwner(stage);
			alerts.setTitle("Error");
			alerts.setHeaderText("There is an Error");
			alerts.setContentText("You must put in the Album Name to Proceed");
			alerts.showAndWait();
		
		}
		else if(user.getlistofAlbums().containsKey(searchalbum.getText()))
		{
			Alert alerts = new Alert(AlertType.INFORMATION);
			alerts.initOwner(stage);
			alerts.setTitle("Error");
			alerts.setHeaderText("There is an Error");
			alerts.setContentText("An album already exists with this Name");
			alerts.showAndWait();
		}
		else if(listview.getItems().isEmpty())
		{
			Alert alerts = new Alert(AlertType.INFORMATION);
			alerts.initOwner(stage);
			alerts.setTitle("Error");
			alerts.setHeaderText("There is an Error");
			alerts.setContentText("There are no Photos to add from your search Query");
			alerts.showAndWait();
		}
		else
		{
			Album temp = new Album(searchalbum.getText());
			TreeItem<String> al = new TreeItem<String>(temp.getalbumName());
			
			 for(String i : user.getlistofAlbums().keySet())
		        {
		        	for (String j: user.getlistofAlbums().get(i).getlistofPhotos().keySet())
		        	{
		        		Photo jp = user.getlistofAlbums().get(i).getlistofPhotos().get(j);
		        		
		        		for(String k: listview.getItems())
		        		{
		        			if(jp.getCaption().equals(k))
		        			{
		        				if(!temp.getlistofPhotos().containsKey(jp.getCaption()))
		        				{
			        				temp.addPhoto(jp);
			        				
			        				TreeItem<String> ph = new TreeItem<String>(jp.getCaption());
			        				al.getChildren().add(ph);
		        				}
		        			}
		        		}
		        	}
		        }
			 user.addAlbum(temp);
			 treeview.getRoot().getChildren().add(al);
			 listview.getItems().clear();
		}
	}
	/**
	 * this method allows you to search by 1 tag or 2 tags
	 * 
	 * @param e button pressed
	 */
	public void searchbyTags(ActionEvent e)
	{
		listview.getItems().clear();
		HashSet<String> list = new HashSet<String>();
		
		if(first.getValue() == null || firsts.getText().equals(""))
		{
			Alert alerts = new Alert(AlertType.INFORMATION);
			alerts.initOwner(stage);
			alerts.setTitle("Error");
			alerts.setHeaderText("There is an Error");
			alerts.setContentText("You must Put in the tag type and value!");
			alerts.showAndWait();
			
		}
		else if(andor.getValue() != null && (andor.getValue().equals("and") || andor.getValue().equals("or")) && (second.getValue() == null || seconds.getText().equals("")))
		{
			Alert alerts = new Alert(AlertType.INFORMATION);
			alerts.initOwner(stage);
			alerts.setTitle("Error");
			alerts.setHeaderText("There is an Error");
			alerts.setContentText("If you are going to use a second tag Type you must select one along with its value");
			alerts.showAndWait();
		
		}
		else
		{
			if(andor.getValue() == null || andor.getValue().equals("one Tag search"))//one tag
			{
				String tagtype = first.getValue();
				String tagvalue = firsts.getText();
				 for(String i : user.getlistofAlbums().keySet())
			        {
			        	for (String j: user.getlistofAlbums().get(i).getlistofPhotos().keySet())
			        	{
			        		Photo jp = user.getlistofAlbums().get(i).getlistofPhotos().get(j);
			        		
			        		if(jp.doIhaveTag(tagtype, tagvalue))
			        		{
			        			list.add(jp.getCaption());
			        		}
			        	}
			        }
			}
			else if(andor.getValue().equals("and"))
			{
				String tagtype = first.getValue();
				String tagvalue = firsts.getText();
				String tagtype2 = second.getValue();
				String tagvalue2 = seconds.getText();
				
				for(String i : user.getlistofAlbums().keySet())
		        {
		        	for (String j: user.getlistofAlbums().get(i).getlistofPhotos().keySet())
		        	{
		        		Photo jp = user.getlistofAlbums().get(i).getlistofPhotos().get(j);
		        		
		        		if(jp.getlistofTags().containsKey(tagtype) && jp.getlistofTags().containsKey(tagtype2))
		        		{
		        			if(jp.getlistofTags().get(tagtype).contains(tagvalue) && jp.getlistofTags().get(tagtype2).contains(tagvalue2))
		        			{
		        				list.add(jp.getCaption());
		        			}
		        		}
		        	}
		        }
				
			}
			else
			{
				String tagtype = first.getValue();
				String tagvalue = firsts.getText();
				String tagtype2 = second.getValue();
				String tagvalue2 = seconds.getText();
				
				for(String i : user.getlistofAlbums().keySet())
		        {
		        	for (String j: user.getlistofAlbums().get(i).getlistofPhotos().keySet())
		        	{
		        		Photo jp = user.getlistofAlbums().get(i).getlistofPhotos().get(j);
		        		
		        		if(jp.getlistofTags().containsKey(tagtype) || jp.getlistofTags().containsKey(tagtype2))
		        		{
		        			if(jp.getlistofTags().get(tagtype) != null && jp.getlistofTags().get(tagtype).contains(tagvalue))
		        			{
		        				list.add(jp.getCaption());
		        			}
		        			if(jp.getlistofTags().get(tagtype2) != null && jp.getlistofTags().get(tagtype2).contains(tagvalue2))
		        			{
		        				list.add(jp.getCaption());
		        			}
		        		}
		        	}
		        }
			}
			
			ArrayList<String> tem = new ArrayList<String>();
			tem.addAll(list);
			listview.getItems().addAll(FXCollections.observableArrayList(tem));
		
		}
	}
	

	
}
