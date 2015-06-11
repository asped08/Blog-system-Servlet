
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
public class EditPostServlet extends HttpServlet {

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
        String postNo = request.getParameter("id");
        String conditionStatus = request.getParameter("condition");
        FileWriter filewrite;

        if (conditionStatus.equals("edit")) {
            try {

                FileReader fileReader = new FileReader(
                        "../webapps/ProjectBlog/Documents/post" + postNo + ".txt");
                Object obj = parser.parse(fileReader);

                JSONObject jsonObject = (JSONObject) obj;

                request.setAttribute("editingPost", jsonObject);
                request.getRequestDispatcher("editpost.jsp").forward(request, response);

            } catch (ParseException ex) {
                Logger.getLogger(EditPostServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (conditionStatus.equals("save")) {

            try {
                FileReader fileReader = new FileReader(
                        "../webapps/ProjectBlog/Documents/post" + postNo + ".txt");
                Object readerObj = parser.parse(fileReader);
                String titleName = request.getParameter("topic");
                String postContent = request.getParameter("content");
                String[] commentApprove = request.getParameterValues("commentApprove");
                
                JSONObject obj = (JSONObject) readerObj;
                obj.remove("topic");
                obj.put("topic", titleName);
                obj.remove("content");
                obj.put("content", postContent);

                if (commentApprove !=null) {
                    JSONArray comments = (JSONArray) obj.get("Comments List");
                    for (String commentApprove1 : commentApprove) {
                        int commentNo = Integer.parseInt(commentApprove1);
                        JSONObject commentObj = (JSONObject) comments.get(commentNo);
                        commentObj.put("approve", "true");
                        comments.set(commentNo, commentObj);

                       
                    }
                    obj.put("Comments List", comments);

                }
                fileReader.close();

                filewrite = new FileWriter("../webapps/ProjectBlog/Documents/post" + postNo + ".txt");

                filewrite.write(obj.toJSONString());

                filewrite.close();

            } catch (ParseException ex) {
                Logger.getLogger(EditPostServlet.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                response.sendRedirect("WelcomeHome");
            }
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
