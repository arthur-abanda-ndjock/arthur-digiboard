import ErrorContent from '@/components/error/ErrorContent'
import Head from 'next/head'
import React from 'react'

const error504 = () => {
  return (
    <>
    <Head>
      <title>Digiboard - Error 504</title>
      <meta name="description" content="Generated by create next app" />
      <meta name="viewport" content="width=device-width, initial-scale=1" />
      <link rel="icon" href="assets/favicon.png" />
    </Head>
    <ErrorContent imgSrc={"assets/images/error-504.png"} alt={'504'} subtitle={'Gateway Timeout'}/>
    </>
  )
}

export default error504