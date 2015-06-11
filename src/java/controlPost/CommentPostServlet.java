
package controlPost;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Supun Athukorala
 */
public class CommentPostServlet extends HttpServlet {

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
        String postNo = request.getParameter("post No");
        String commentContent = request.getParameter("Commentcontent");
        try {

            FileReader fileReader = new FileReader(
                        "../webapps/ProjectBlog/Documents/post" + postNo + ".txt");
                Object readerObj = parser.parse(fileReader);

            JSONObject jsonObject = (JSONObject) readerObj;
             JSONArray comments = (JSONArray) jsonObject.get("Comments List");
             
              JSONObject commentObj = new JSONObject();
        commentObj.put("approve", "false");
        commentObj.put("content", commentContent);
            comments.add(commentObj);
           
            
            jsonObject.put("Comments List", comments);
            
            fileReader.close();
               

           FileWriter filewrite = new FileWriter("../webapps/ProjectBlog/Documents/post" + postNo + ".txt");

                filewrite.write(jsonObject.toJSONString());
                filewrite.close();
                
                 String postId = "ViewPost?id=" + postNo;
         response.sendRedirect(postId);
        } catch (ParseException ex) {
            Logger.getLogger(EditPostServlet.class.getName()).log(Level.SEVERE, null, ex);
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
