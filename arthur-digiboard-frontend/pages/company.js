import Footer from '../components/footer/Footer';
import CompanyHeader from '../components/header/CompanyHeader';
import CompanyFilter from '../components/filter/CompanyFilter';
import CompanyTable from '../components/tables/CompanyTable';
import Head from 'next/head';

const CompanyMainContent = () => {
    return (
        <>
            <Head>
                <title>Digiboard - Company</title>
                <meta name="description" content="Generated by create next app" />
                <meta name="viewport" content="width=device-width, initial-scale=1" />
                <link rel="icon" href="assets/favicon.png" />
            </Head>
            <div className="main-content">
                <div className="row">
                    <div className="col-12">
                        <div className="panel">
                            <CompanyHeader />
                            <div className="panel-body">
                                <CompanyFilter />
                                <CompanyTable />
                            </div>
                        </div>
                    </div>
                </div>

                <Footer />
            </div>
        </>


    )
}

export default CompanyMainContent