
package Showpost;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Supun Athukorala
 */
public class ShowPostServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        JSONParser parser = new JSONParser();
        String count0 = request.getParameter("count");
        if(count0 !=null){
        
        
        }
        ArrayList postList = new ArrayList();
        //int maxPost =Integer.parseInt(count0);
        int count = 1;
       
        if (count0 == null) {
            System.out.println(Paths.get(".").toAbsolutePath().normalize().toString());
            
            File folder = new File("../webapps/ProjectBlog/Documents");
            if(folder.exists()){
            int noOfPost = folder.listFiles().length;
            count = noOfPost;
                System.out.println("post no "+count);
            }
            else{
            count =0;
            }
            
            
            File f = new File("../webapps/ProjectBlog/Documents/post" + count + ".txt");
           
            int postCount = 0;
            while (f.exists() && !f.isDirectory()) {
                System.out.println("post no "+count);
                if (postCount == 10) {
                    break;
                }
                try {

                    Object obj = parser.parse(new FileReader(
                            "../webapps/ProjectBlog/Documents/post" + count + ".txt"));

                    JSONObject jsonObject = (JSONObject) obj;
                    postList.add(jsonObject);
                    count--;
                    System.out.println("post no "+count);

                } catch (IOException | ParseException e) {
                }

                f = new File("../webapps/ProjectBlog/Documents/post" + count + ".txt");
                 postCount++;
            }
            
            request.setAttribute("result", postList);
            request.getRequestDispatcher("home.jsp").forward(request, response);
            
           
        } else if (count0.equals("all")) {
            count=1;
            File f = new File("../webapps/ProjectBlog/Documents/post" + count + ".txt");
            while (f.exists() && !f.isDirectory()) {
                try {

                    Object obj = parser.parse(new FileReader(
                            "../webapps/ProjectBlog/Documents/post" + count + ".txt"));

                    JSONObject jsonObject = (JSONObject) obj;
                    postList.add(jsonObject);
                    count++;

                } catch (IOException | ParseException e) {
                }

                f = new File("../webapps/ProjectBlog/Documents/post" + count + ".txt");
            }
            
            request.setAttribute("result", postList);
            request.getRequestDispatcher("home.jsp").forward(request, response);

        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
