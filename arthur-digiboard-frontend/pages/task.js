import Footer from '../components/footer/Footer';
import TaskHeader from '../components/header/TaskHeader';
import TaskTableFilter from '../components/filter/TaskTableFilter';
import TaskTable from '../components/tables/TaskTable';
import AddNewTaskModal from '../components/modal/AddNewTaskModal';
import EditTaskModal from '../components/modal/EditTaskModal';
import ViewTaskModal from '../components/modal/ViewTaskModal';
import Head from 'next/head';

const TaskMainContent = () => {    
  return (
    <>
    <Head>
      <title>Digiboard - Task</title>
      <meta name="description" content="Generated by create next app" />
      <meta name="viewport" content="width=device-width, initial-scale=1" />
      <link rel="icon" href="assets/favicon.png" />
    </Head>
    <div className="main-content">
        <div className="row">
            <div className="col-12">
                <div className="panel">
                    <TaskHeader/>
                    <div className="panel-body">
                        <TaskTableFilter/>
                        <TaskTable/>
                    </div>
                </div>
            </div>
        </div>
        <AddNewTaskModal/>
        <EditTaskModal/>
        <ViewTaskModal/>
       <Footer/>
    </div>
    </>
  )
}

export default TaskMainContent