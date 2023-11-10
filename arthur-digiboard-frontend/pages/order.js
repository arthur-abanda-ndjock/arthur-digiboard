import React from 'react'
import Footer from '../components/footer/Footer'
import OrderHeader from '../components/header/OrderHeader'
import HeaderBtn from '../components/header/HeaderBtn'
import OrderTableFilter from '../components/filter/OrderTableFilter'
import OrderListTable from '../components/tables/OrderListTable'
import Head from 'next/head'

const OrderMainContent = () => {
    return (
        <>
        <Head>
        <title>Digiboard - Order</title>
        <meta name="description" content="Generated by create next app" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link rel="icon" href="assets/favicon.png" />
        </Head>
            <div className="main-content">
                <div className="row g-4">
                    <div className="col-12">
                        <div className="panel">
                            <OrderHeader />
                            <div className="panel-body">
                                <HeaderBtn />
                                <OrderTableFilter />
                                <OrderListTable />
                            </div>
                        </div>
                    </div>
                </div>
                <Footer />
            </div>
        </>
    )
}

export default OrderMainContent