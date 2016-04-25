package cm.service;

import cm.dao.HibernateBaseDao;
import cm.entity.AnalysisNote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 类名：cm.service.AnalysisNoteService
 * 创建者： CM .
 * 创建时间：2016/4/25
 */
@Service
public class AnalysisNoteService {
    @Autowired
    private HibernateBaseDao baseDao;

    /**
     * 添加笔记
     *
     * @param analysisNote
     * @return
     */
    public Long addNote(AnalysisNote analysisNote) {
        baseDao.add(analysisNote);
        return analysisNote.getId();
    }

    /**
     * 根据模块获取记录的笔记
     *
     * @param module
     * @return
     */
    public List<AnalysisNote> findByModule(String module) {
        String hql = "from  AnalysisNote an where an.module=? ";
        return (List<AnalysisNote>) baseDao.queryForList(hql, module);
    }
}
