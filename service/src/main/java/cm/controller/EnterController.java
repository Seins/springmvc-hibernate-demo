package cm.controller;

import cm.domainEnum.ModuleEnmu;
import cm.entity.AnalysisNote;
import cm.service.AnalysisNoteService;
import cm.web.MediaTypes;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.HashMap;

/**
 * Created by CM on 16/4/16.
 */
@Controller
public class EnterController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(EnterController.class);

    @Autowired
    private AnalysisNoteService noteService;

    /**
     * 页面数据注入,不同的模块,主体内容的页面URI不同
     *
     * @param modelMap
     * @param module
     * @return
     */
    @RequestMapping("/{module}")
    public String index(ModelMap modelMap, @PathVariable(value = "module") ModuleEnmu module) {
        switch (module) {
            case CONCURRENT:
                modelMap.put("module", ModuleEnmu.CONCURRENT);
                modelMap.put("notes", noteService.findByModule(module.toString()));
                modelMap.put("uri", "/WEB-INF/view/concurrent.jsp");
                break;
            case TIME_OUT:
                modelMap.put("module", ModuleEnmu.TIME_OUT);
                modelMap.put("notes", noteService.findByModule(module.toString()));
                modelMap.put("uri", "/WEB-INF/view/timeout.jsp");
                break;
            case ALL_INTERFACE_FREQUENCE:
                modelMap.put("module", ModuleEnmu.ALL_INTERFACE_FREQUENCE);
                modelMap.put("notes", noteService.findByModule(module.toString()));
                modelMap.put("si", true);
                modelMap.put("uri", "/WEB-INF/view/interface.jsp");
                break;
            case INTERFACE_RESPONSE:
                modelMap.put("module", ModuleEnmu.INTERFACE_RESPONSE);
                modelMap.put("notes", noteService.findByModule(module.toString()));
                modelMap.put("si", true);
                modelMap.put("uri", "/WEB-INF/view/responsetime.jsp");
                break;
            case CACHE_DATA_AMOUNT:
                modelMap.put("module", ModuleEnmu.CACHE_DATA_AMOUNT);
                modelMap.put("notes", noteService.findByModule(module.toString()));
                modelMap.put("uri", "/WEB-INF/view/cache.jsp");
                break;
            default:
                modelMap.put("module", ModuleEnmu.CONCURRENT);
                modelMap.put("notes", noteService.findByModule(ModuleEnmu.CONCURRENT.toString()));
                modelMap.put("uri", "/WEB-INF/view/concurrent.jsp");
        }
        return "forward:/";
    }

    @ResponseBody
    @RequestMapping(value = "/note/add", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
    public String addNote(ModelMap modelMap, @RequestParam String noteContent, @RequestParam Long logTime,
                          @RequestParam Long noteTime, @RequestParam String module) {
        try {
            AnalysisNote note = new AnalysisNote();
            note.setLogTime(new Timestamp(logTime));
            note.setNoteTime(new Timestamp(noteTime));
            note.setModule(module);
            note.setNoteContent(noteContent);
            noteService.addNote(note);
            this.success(modelMap);
        } catch (RuntimeException ex) {
            this.failed(modelMap, ex.getMessage());
            LOGGER.error("add note occur error:", ex);

        } catch (Exception ex) {
            this.failed(modelMap, "服务器发生错误，请求失败!");
            LOGGER.error("add note occur error:", ex);
        }

        return JSON.toJSONString(modelMap);
    }
}
